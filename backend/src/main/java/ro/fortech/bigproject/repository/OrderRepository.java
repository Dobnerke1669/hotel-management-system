package ro.fortech.bigproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.bigproject.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
