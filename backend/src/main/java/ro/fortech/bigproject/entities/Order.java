package ro.fortech.bigproject.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private Long roomNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    private boolean done = false;

    public Order(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void addItem(Product product, int quantity) {
        OrderItem orderItem = new OrderItem(this, product, quantity);
        items.add(orderItem);
        totalPrice = totalPrice.add(orderItem.getTotalPrice());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", roomNumber=" + roomNumber +
                ", items=" + items +
                ", done=" + done +
                '}';
    }
}
