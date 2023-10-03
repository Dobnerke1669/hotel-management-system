package ro.fortech.bigproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.bigproject.entities.ERoomType;
import ro.fortech.bigproject.entities.Reservation;
import ro.fortech.bigproject.entities.Room;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.payload.request.PreReservation;
import ro.fortech.bigproject.repository.ReservationRepository;
import ro.fortech.bigproject.repository.RoomRepository;
import ro.fortech.bigproject.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final UserRepository userRepository;


    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !end1.before(start2) && !end1.equals(start2) && !end2.before(start1) && !end2.equals(start1);
    }

    public long numberOfDays(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long millisecondsDifference = endTime - startTime;
        return millisecondsDifference / (24 * 60 * 60 * 1000);
    }
    public ERoomType getTypeOfRoom(String roomType) {
        return switch (roomType.toLowerCase()) {
            case "single" -> ERoomType.SINGLE;
            case "double" -> ERoomType.DOUBLE;
            case "triple" -> ERoomType.TRIPLE;
            case "family" -> ERoomType.FAMILY;
            default -> throw new IllegalStateException("Unexpected value: " + roomType);
        };
    }

    public Room availableRoom(PreReservation reservation) {
        String roomType = reservation.getRoomType();
        List<Room> roomsAvailable = roomRepository.findAllByRoomType(getTypeOfRoom(roomType));
        for (Room r : roomsAvailable) {
            boolean availableFlag = true;
            List<Reservation> roomReservations = r.getReservations();
            for (Reservation roomReservation : roomReservations) {
                if (datesOverlap(reservation.getStartDate(), reservation.getEndDate(), roomReservation.getStartDate(), roomReservation.getEndDate())) {
                    availableFlag = false;
                    break;
                }
            }
            if (availableFlag) {
                return r;
            }
        }
        return null;
    }

    public Reservation addReservation(PreReservation reservation) {
        Reservation finalReservation = new Reservation(reservation.getStartDate(), reservation.getEndDate());
        User user = userRepository.findById(reservation.getClientId()).orElseThrow();
        finalReservation.setUser(user);
        Room room = availableRoom(reservation);
        if(room.equals(null)){
            return null;
        }
        finalReservation.setRoom(room);
        room.addReservationToList(finalReservation);
        BigDecimal nrNights = new BigDecimal(numberOfDays(reservation.getStartDate(), reservation.getEndDate()));
        BigDecimal totalPrice = nrNights.multiply(room.getPricePerNight());
        finalReservation.setTotalPrice(totalPrice);
        return reservationRepository.save(finalReservation);
    }

    public BigDecimal viewTotalPrice(PreReservation reservation) {
        Room room = availableRoom(reservation);
        if (room == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal nrNights =  new BigDecimal(numberOfDays(reservation.getStartDate(), reservation.getEndDate()));
        return nrNights.multiply(room.getPricePerNight());

    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getClientReservations(Long clientId) {
        return reservationRepository.findAllByUserId(clientId);
    }


}
