package ro.fortech.bigproject.payload.response;

import java.math.BigDecimal;
import java.util.Date;

public class ReservationResponse {

    private Date startDate;

    private Date endDate;

    private BigDecimal totalPrice;

    private Long userId;

    private String roomType;
     private Long id;


    public ReservationResponse(Date startDate, Date endDate, BigDecimal totalPrice, Long userId, String roomType, Long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.roomType = roomType;
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
