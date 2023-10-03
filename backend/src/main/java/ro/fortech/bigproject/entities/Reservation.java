package ro.fortech.bigproject.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date startDate;

    private Date endDate;

    private BigDecimal totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public Reservation(User user, Date startDate, Date endDate) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(User user, Date startDate, Date endDate, BigDecimal totalPrice, Room room) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.room = room;
    }

    public Reservation() {

    }

    public Reservation(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
