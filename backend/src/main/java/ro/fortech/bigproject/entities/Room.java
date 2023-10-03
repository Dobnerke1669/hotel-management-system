package ro.fortech.bigproject.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
public class Room {

    @Id
    private Long number;

    @Enumerated(EnumType.STRING)
    private ERoomType roomType;

    private BigDecimal pricePerNight;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;


    public Room(Long number, ERoomType roomType, BigDecimal pricePerNight, List<Reservation> reservations) {
        this.number = number;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.reservations = reservations;
    }

    public Room() {

    }

    public ERoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(ERoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservationToList(Reservation reservation) {
        List<Reservation> reservationList = this.getReservations();
        reservationList.add(reservation);
        this.setReservations(reservationList);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

}
