package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@NamedQueries({
        @NamedQuery(name = "Ticket.GET_ALL", query = "SELECT t FROM Ticket t")
})
public class Ticket implements IEntity {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "showtime_id")
    private ShowTime showTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JoinColumn(name = "reservation", referencedColumnName = "reservation_id")
    @ManyToOne(optional = false)
    private Reservation reservation;

    public Ticket() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return id == ticket.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}