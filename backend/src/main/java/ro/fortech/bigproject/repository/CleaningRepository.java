package ro.fortech.bigproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.bigproject.entities.NotificationCleaning;

@Repository
public interface CleaningRepository extends JpaRepository<NotificationCleaning, Long> {
}
