package ro.fortech.bigproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.bigproject.entities.ERoomType;
import ro.fortech.bigproject.entities.Room;
import ro.fortech.bigproject.repository.RoomRepository;

import java.util.List;


@Service
public class RoomReservationService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomReservationService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);

    }

    public void deleteRoom(Long roomNumber) {
        roomRepository.deleteById(roomNumber);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }


    public Room getRoomByNumber(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Room is not found."));
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
}
