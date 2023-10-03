package ro.fortech.bigproject.entities;


import jakarta.persistence.*;

@Entity
public class NotificationCleaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    private User cleaner;

    public NotificationCleaning(String details) {
        this.details = details;
    }

    public NotificationCleaning(String details, Room room) {
        this.details = details;
        this.room = room;
    }

    public NotificationCleaning(Long id, String details, Room room, boolean done, User assignedTo) {
        this.id = id;
        this.details = details;
        this.room = room;
        this.done = done;
        this.cleaner = assignedTo;
    }

    public NotificationCleaning() {

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Room getRoom() {
        return room;
    }

    public User getCleaner() {
        return cleaner;
    }

    public void setCleaner(User cleaner) {
        this.cleaner = cleaner;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
}
