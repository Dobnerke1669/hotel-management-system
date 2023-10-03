package ro.fortech.bigproject.payload.response;

import java.math.BigDecimal;

public class OrderResponse {

    private Long id;
    private BigDecimal totalPrice;
    private Long roomNumber;
    private boolean done;

    public OrderResponse(Long id, BigDecimal totalPrice, Long roomNumber, boolean done) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.roomNumber = roomNumber;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
