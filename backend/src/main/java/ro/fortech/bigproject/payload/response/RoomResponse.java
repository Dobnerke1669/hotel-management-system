package ro.fortech.bigproject.payload.response;

import java.math.BigDecimal;

public class RoomResponse {

    private Long number;

    private String roomType;

    private BigDecimal pricePerNight;

    public RoomResponse(Long number, String roomType, BigDecimal pricePerNight) {
        this.number = number;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public RoomResponse() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
