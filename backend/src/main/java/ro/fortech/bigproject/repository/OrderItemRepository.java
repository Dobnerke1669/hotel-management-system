package ro.fortech.bigproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fortech.bigproject.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
