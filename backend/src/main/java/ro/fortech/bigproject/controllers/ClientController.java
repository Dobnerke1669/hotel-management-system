package ro.fortech.bigproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.*;
import ro.fortech.bigproject.payload.request.*;
import ro.fortech.bigproject.payload.response.MenuResponse;
import ro.fortech.bigproject.payload.response.MessageResponse;
import ro.fortech.bigproject.payload.response.ReservationResponse;
import ro.fortech.bigproject.services.*;
import ro.fortech.bigproject.services.validators.exceptions.BlankException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
@PreAuthorize("hasRole('CLIENT')")
public class ClientController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ImageDataService imageDataService;

    @Autowired
    private CleaningService cleaningService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    @Operation(summary = "Add Reservation", description = "Add a new reservation")
    @PostMapping("/addReservation")
    public ResponseEntity<MessageResponse> addReservation(@RequestBody ClientAddReservationRequest clientAddReservationRequest) {
        PreReservation reservation = new PreReservation(clientAddReservationRequest.getClientId(), clientAddReservationRequest.getStartDate(), clientAddReservationRequest.getEndDate(), clientAddReservationRequest.getRoomType());
        GenericService<ClientAddReservationRequest> genericService = new GenericService<>();
        try {
            genericService.checkIfBlank(clientAddReservationRequest);
        } catch (BlankException e) {
            return ResponseEntity.status(469).body(new MessageResponse(e.getMessage()));
        }
        reservationService.addReservation(reservation);
        return ResponseEntity.ok(new MessageResponse("Reservation added successfully"));
    }

    @Operation(summary = "Delete Reservation", description = "Delete a reservation")
    @DeleteMapping("/deleteReservation/{id}")
    public ResponseEntity<MessageResponse> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(new MessageResponse("Reservation deleted successfully"));
    }

    @Operation(summary = "Get Reservations", description = "Get all reservations for the specific client")
    @GetMapping("/getReservations/{id}")
    public List<ReservationResponse> getReservations(@PathVariable Long id) {
        List<Reservation> reservations = reservationService.getClientReservations(id);
        List<ReservationResponse> reservationResponses = new ArrayList<>();

        for(Reservation reservation : reservations){
            ReservationResponse reservationResponse = new ReservationResponse(reservation.getStartDate(),reservation.getEndDate(),reservation.getTotalPrice(),reservation.getUser().getId(),reservation.getRoom().getRoomType().toString(),reservation.getId());
            reservationResponses.add(reservationResponse);
        }
        return reservationResponses;

    }

    @Operation(summary = "View Price", description = "View the total price for the reservation")
    @PostMapping("/viewPrice")
    public BigDecimal viewTotalPrice(@Valid @RequestBody ViewTotalPriceRequest viewTotalPriceRequest) {
        PreReservation preReservation = new PreReservation(viewTotalPriceRequest.getStartDate(), viewTotalPriceRequest.getEndDate(), viewTotalPriceRequest.getRoomType());
        return reservationService.viewTotalPrice(preReservation);
    }

    @PostMapping("/sendNotification")
    public ResponseEntity<MessageResponse> sendNotification(@Valid @RequestBody SendNotificationRequest notification) {
        IntermediateNotification notificationCleaning = new IntermediateNotification(notification.getDetails(), notification.getRoomNumber());
        cleaningService.addNotification(notificationCleaning);
        return ResponseEntity.ok(new MessageResponse("Notification sent successfully"));
    }

    @PostMapping("/addOrder")
    public ResponseEntity<MessageResponse> makeOrder(@Valid @RequestBody AddOrderRequest addOrderRequest) {
        orderService.addOrder(addOrderRequest);
        return ResponseEntity.ok(new MessageResponse("Order made successfully"));
    }

    @GetMapping("/getProducts")
    public List<MenuResponse> getProducts() {
        List<Product> products = productService.findAllProducts();
        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Product product : products) {
            MenuResponse menuResponse = new MenuResponse(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getImage().getId(), product.getDescription(), product.isAvailable());
            menuResponses.add(menuResponse);
        }
        return menuResponses;
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImageByName(@PathVariable Long id) {
        ImageData image = imageDataService.getImage(id);
        if (image.getType().contains("jpeg")) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/jpeg"))
                    .body(image.getImageData());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image.getImageData());
    }

}
