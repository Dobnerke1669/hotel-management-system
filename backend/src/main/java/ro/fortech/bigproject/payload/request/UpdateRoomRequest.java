package ro.fortech.bigproject.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class UpdateRoomRequest {
    @Schema(example = "1", required = true)
    private Long number;
    @Schema(example = "double", required = true)
    private String roomType;
    @Schema(example = "100", required = true)
    private BigDecimal pricePerNight;

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
