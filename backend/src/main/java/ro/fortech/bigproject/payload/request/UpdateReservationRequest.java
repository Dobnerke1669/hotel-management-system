package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class UpdateReservationRequest {

    @Schema(example = "1", required = true)
    private Long id;
    @Schema(example = "2024-05-15", required = true)
    private Date startDate;
    @Schema(example = "2024-05-18", required = true)
    private Date endDate;
    @Schema(example = "Single", required = true)
    private String roomType;
    @Schema(example = "105", required = true)
    private double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
