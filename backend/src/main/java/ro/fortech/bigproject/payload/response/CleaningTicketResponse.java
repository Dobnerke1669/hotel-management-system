package ro.fortech.bigproject.payload.response;

public class CleaningTicketResponse {

    private Long id;

    private String details;
    private Long roomNumber;
    private boolean done;
    private String cleanerName;

    public CleaningTicketResponse(Long id, String details, Long roomNumber, boolean done, String cleanerName) {
        this.id = id;
        this.details = details;
        this.roomNumber = roomNumber;
        this.done = done;
        this.cleanerName = cleanerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getCleanerId() {
        return cleanerName;
    }

    public void setCleanerId(String cleanerName) {
        this.cleanerName = cleanerName;
    }
}
