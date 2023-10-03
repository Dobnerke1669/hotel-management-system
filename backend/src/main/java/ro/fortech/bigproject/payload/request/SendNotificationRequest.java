package ro.fortech.bigproject.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;

public class SendNotificationRequest {
    @Schema(example = "Room 1 needs cleaning", required = true)
    private String details;
    @Schema(example = "1", required = true)
    private Long roomNumber;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }
}
