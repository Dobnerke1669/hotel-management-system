package ro.fortech.bigproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.Room;
import ro.fortech.project.repository.RoomRepository;
import ro.fortech.project.services.RoomReservationService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
import static ro.fortech.project.entities.ERoomType.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomReservationServiceTest {

    @InjectMocks
    private RoomReservationService roomReservationService;


    @Mock
    private RoomRepository roomRepo;


    @Test
    public void testAddRoom(){
        Room room = new Room(345L,DOUBLE,BigDecimal.valueOf(160.00),null);
        when(roomRepo.save(room)).thenReturn(room);
        Room savedRoom = roomReservationService.addRoom(room);
        assertSame(room,savedRoom);
        verify(roomRepo, times(1)).save(room);
    }


    @Test
    public void testUpdateRoom(){
        Room room = new Room(345L, DOUBLE, BigDecimal.valueOf(160.00), null);
        when(roomRepo.save(room)).thenReturn(room);
        RoomReservationService roomReservationService = new RoomReservationService(roomRepo);
        Room updatedRoom = roomReservationService.updateRoom(room);
        assertSame(room, updatedRoom);
        verify(roomRepo, times(1)).save(updatedRoom);

    }
    @Test
    public void testDeleteRoom(){
        Long roomNumber = 345L;
        RoomReservationService roomReservationService = new RoomReservationService(roomRepo);
        roomReservationService.deleteRoom(roomNumber);
        verify(roomRepo, atLeastOnce()).deleteById(roomNumber);
    }

    @Test
    public void testFindAllRooms(){

        List<Room> expectedRooms = Arrays.asList(
                new Room(1L, SINGLE, BigDecimal.valueOf(100.00), null),
                new Room(2L, DOUBLE, BigDecimal.valueOf(150.00), null),
                new Room(3L, TRIPLE, BigDecimal.valueOf(200.00), null)
        );
        when(roomRepo.findAll()).thenReturn(expectedRooms);
        RoomReservationService roomReservationService = new RoomReservationService(roomRepo);
        List<Room> actualRooms = roomReservationService.findAllRooms();
        assertEquals(expectedRooms, actualRooms);
        verify(roomRepo, times(1)).findAll();

    }

    @Test
    public void testGetRoomByNumber(){
        Room expectedRoom = new Room(1L, SINGLE, BigDecimal.valueOf(100.00), null);
        when(roomRepo.findById(1L)).thenReturn(Optional.of(expectedRoom));
        RoomReservationService roomReservationService = new RoomReservationService(roomRepo);
        Room actualRoom = roomReservationService.getRoomByNumber(1L);
        assertEquals(expectedRoom, actualRoom);
        verify(roomRepo, times(1)).findById(1L);


    }

}