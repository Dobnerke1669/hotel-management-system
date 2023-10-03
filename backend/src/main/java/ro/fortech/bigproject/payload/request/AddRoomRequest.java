package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public class AddRoomRequest {
    @Schema(example = "1", required = true)
    private Long number;

    @Size(max = 25)
    @Schema(example = "single", required = true)
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
