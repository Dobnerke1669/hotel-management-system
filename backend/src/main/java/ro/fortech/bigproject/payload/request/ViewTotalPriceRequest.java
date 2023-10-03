package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class ViewTotalPriceRequest {
    @Schema(example = "2024-05-05", required = true)
    private Date startDate;
    @Schema(example = "2024-05-10", required = true)
    private Date endDate;
    @Schema(example = "Single", required = true)
    private String roomType;

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
}
