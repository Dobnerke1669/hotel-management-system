package ro.fortech.bigproject.payload.request;

import java.math.BigDecimal;
import java.util.Date;
public class PreReservation {

    private Long id;


    private Long clientId;


    private Date startDate;


    private Date endDate;

    private BigDecimal totalPrice;

    private String roomType;

    public PreReservation(Long clientId, Date startDate, Date endDate, String roomType) {
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
    }

    public PreReservation(Date startDate, Date endDate, String roomType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
    }

    public PreReservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
