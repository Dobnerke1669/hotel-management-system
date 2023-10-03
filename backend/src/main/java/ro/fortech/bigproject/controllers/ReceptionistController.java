package ro.fortech.bigproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.Reservation;
import ro.fortech.bigproject.entities.Role;
import ro.fortech.bigproject.entities.Room;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.payload.request.*;
import ro.fortech.bigproject.payload.response.MessageResponse;
import ro.fortech.bigproject.payload.response.ReservationResponse;
import ro.fortech.bigproject.payload.response.RoomResponse;
import ro.fortech.bigproject.services.ReservationService;
import ro.fortech.bigproject.services.RoomReservationService;
import ro.fortech.bigproject.services.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/receptionist")
@PreAuthorize("hasRole('RECEPTIONIST')")
public class ReceptionistController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomReservationService roomReservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    public ReceptionistController(ReservationService reservationService, RoomReservationService roomReservationService, UserService userService) {
        this.reservationService = reservationService;
        this.roomReservationService = roomReservationService;
        this.userService = userService;
    }

    @Operation(summary = "Add a new room", description = "Adds a new room")
    @PostMapping("/addRoom")
    public ResponseEntity<MessageResponse> addRoom(@RequestBody AddRoomRequest addRoomRequest) {
        Room room = new Room(addRoomRequest.getNumber(), roomReservationService.getTypeOfRoom(addRoomRequest.getRoomType()), new BigDecimal(String.valueOf(addRoomRequest.getPricePerNight())), new ArrayList<>());
        roomReservationService.addRoom(room);
        return ResponseEntity.ok(new MessageResponse("Room added successfully"));
    }

    @Operation(summary = "Update a room", description = "Updates a room")
    @PutMapping("/updateRoom")
    public ResponseEntity<MessageResponse> updateRoom(@Valid @RequestBody UpdateRoomRequest updateRoomRequest) {
        Room updatedRoom = roomReservationService.getRoomByNumber(updateRoomRequest.getNumber());
        updatedRoom.setRoomType(roomReservationService.getTypeOfRoom(updateRoomRequest.getRoomType()));
        updatedRoom.setPricePerNight(updateRoomRequest.getPricePerNight());
        roomReservationService.updateRoom(updatedRoom);
        return ResponseEntity.ok(new MessageResponse("Room updated successfully"));
    }

    @Operation(summary = "Delete a room", description = "Deletes a room")
    @DeleteMapping("/deleteRoom/{number}")
    public ResponseEntity<MessageResponse> deleteRoom(@PathVariable Long number) {
        roomReservationService.deleteRoom(number);
        return ResponseEntity.ok(new MessageResponse("Room deleted successfully"));
    }

    @Operation(summary = "Add a new reservation", description = "Adds a new reservation")
    @PostMapping("/addReservation")
    public ResponseEntity<MessageResponse> addReservation(@Valid @RequestBody AddReservationRequest addReservationRequest) {
        if (userService.findByFirstNameAndLastName(addReservationRequest.getFirstName(), addReservationRequest.getLastName()) == null) {
            String newUsername = addReservationRequest.getFirstName().toLowerCase() + addReservationRequest.getLastName().toLowerCase();
            User newUser = new User(newUsername, addReservationRequest.getEmail(), encoder.encode("12345678"), addReservationRequest.getFirstName(), addReservationRequest.getLastName(), true);
            List<Role> roles = userService.insertUserRole();
            newUser.setRoles(roles);
            try {
                userService.createUser(newUser);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
            }
        }
        User user = userService.findByFirstNameAndLastName(addReservationRequest.getFirstName(), addReservationRequest.getLastName());
        PreReservation reservation = new PreReservation(user.getId(), addReservationRequest.getStartDate(), addReservationRequest.getEndDate(), addReservationRequest.getRoomType());
        if (reservationService.addReservation(reservation) == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new MessageResponse("Reservation made successfully"));
    }

    @Operation(summary = "Update a reservation", description = "Updates a reservation")
    @PutMapping("/updateReservation")
    public ResponseEntity<MessageResponse> updateReservation(@Valid @RequestBody UpdateReservationRequest updateReservationRequest) {
        Reservation reservation = reservationService.getReservationById(updateReservationRequest.getId());
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationService.deleteReservation(updateReservationRequest.getId());
        PreReservation newReservation = new PreReservation(reservation.getUser().getId(), updateReservationRequest.getStartDate(), updateReservationRequest.getEndDate(), updateReservationRequest.getRoomType());
        reservationService.addReservation(newReservation);
        return ResponseEntity.ok(new MessageResponse("Reservation updated successfully"));
    }

    @Operation(summary = "Delete a reservation", description = "Deletes a reservation")
    @DeleteMapping("/deleteReservation/{id}")
    public ResponseEntity<MessageResponse> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(new MessageResponse("Reservation deleted successfully"));
    }

    @Operation(summary = "Get all reservations", description = "Get all reservations")
    @GetMapping("/getReservations")
    public List<ReservationResponse> getReservations() {

        List<Reservation> reservations = reservationService.getReservations();
        List<ReservationResponse> reservationResponses = new ArrayList<>();

        for(Reservation reservation : reservations){
            ReservationResponse reservationResponse = new ReservationResponse(reservation.getStartDate(),reservation.getEndDate(),reservation.getTotalPrice(),reservation.getUser().getId(),reservation.getRoom().getRoomType().toString(),reservation.getId());
            reservationResponses.add(reservationResponse);
        }
        return reservationResponses;

    }

    @Operation(summary = "Get all rooms", description = "Get all rooms")
    @GetMapping("/getRooms")
    public List<RoomResponse> getRooms() {
        List<Room> rooms = roomReservationService.findAllRooms();
        List<RoomResponse> roomResponses = new ArrayList<>();

        for(Room room : rooms){
            String roomType = String.valueOf(room.getRoomType());
                    RoomResponse roomResponse = new RoomResponse(room.getNumber(),roomType,room.getPricePerNight());
                    roomResponses.add(roomResponse);
        }
        return roomResponses;

    }

    @Operation(summary = "Get room by id", description = "Get room by id")
    @GetMapping("/getRoom/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomReservationService.getRoomByNumber(id);
    }

}
