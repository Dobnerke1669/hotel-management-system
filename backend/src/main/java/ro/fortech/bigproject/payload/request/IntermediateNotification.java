package ro.fortech.bigproject.payload.request;

public class IntermediateNotification {

    private String details;

    private Long roomNumber;

    public IntermediateNotification(String details, Long roomNumber) {
        this.details = details;
        this.roomNumber = roomNumber;
    }

    public IntermediateNotification() {

    }

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
