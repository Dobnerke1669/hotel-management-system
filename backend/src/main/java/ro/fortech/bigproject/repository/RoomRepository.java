package ro.fortech.bigproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.bigproject.entities.ERoomType;
import ro.fortech.bigproject.entities.Room;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByRoomType(ERoomType roomType);
}
