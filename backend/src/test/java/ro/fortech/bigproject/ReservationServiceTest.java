package ro.fortech.bigproject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.Room;
import ro.fortech.project.payload.request.PreReservation;
import ro.fortech.project.repository.ReservationRepository;
import ro.fortech.project.repository.RoomRepository;
import ro.fortech.project.repository.UserRepository;
import ro.fortech.project.services.ReservationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static ro.fortech.project.entities.ERoomType.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testDatesOverlapNo(){
        Date startDate1 = new Date(2023,05,21);
        Date endDate1 = new Date(2023,05,25);
        Date startDate2 = new Date(2023,06,21);
        Date endDate2 = new Date(2023,06,25);
        boolean overlap = reservationService.datesOverlap(startDate1,endDate1,startDate2,endDate2);
        assertFalse(overlap, "Dates should not overlap");

    }

    @Test
    public void testDatesOverlapYes(){
        Date startDate1 = new Date(2023,05,21);
        Date endDate1 = new Date(2023,05,25);
        Date startDate2 = new Date(2023,05,19);
        Date endDate2 = new Date(2023,05,23);
        boolean overlap = reservationService.datesOverlap(startDate1,endDate1,startDate2,endDate2);
        assertTrue(overlap, "Dates should overlap");

    }

    @Test
    public void testNumberOfDays(){
        Date startDate = new Date(2023,05,21);
        Date endDate = new Date(2023,05,25);
        long days = reservationService.numberOfDays(startDate,endDate);
        assertEquals(4,days);

    }
    @Test
    public void testAvailableRoom(){
        PreReservation reservation = new PreReservation(new Date(2023,11,21), new Date(2023,11,24),"double");
        when(roomRepository.findAllByRoomType(DOUBLE)).thenReturn(Collections.singletonList(new Room(125L,DOUBLE, BigDecimal.valueOf(160.00),new ArrayList<>())));
        Room room = reservationService.availableRoom(reservation);
        assertNotNull(room);
    }

//    @Test
//    public void testAddReservation(){
//
//        Date startDate = new Date(2023, 9, 1);
//        Date endDate = new Date(2023, 9, 5);
//        long clientId = 1L;
//        BigDecimal pricePerNight = new BigDecimal("100.00");
//
//        PreReservation preReservation = new PreReservation();
//        preReservation.setStartDate(startDate);
//        preReservation.setEndDate(endDate);
//        preReservation.setClientId(clientId);
//
//        User user = new User();
//        user.setId(clientId);
//
//        Room room = new Room();
//        room.setPricePerNight(pricePerNight);
//
//        when(userRepository.findById(clientId)).thenReturn(Optional.of(user));
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(new Reservation(startDate, endDate));
//
//        Reservation resultReservation = reservationService.addReservation(preReservation);
//        verify(userRepository, times(1)).findById(clientId);
//        verify(reservationRepository, times(1)).save(any(Reservation.class));
//
//        assertNotNull(resultReservation);
//        assertEquals(user, resultReservation.getUser());
//        assertEquals(room, resultReservation.getRoom());
//        assertEquals(pricePerNight.multiply(new BigDecimal("4")), resultReservation.getTotalPrice());
//
//    }

}