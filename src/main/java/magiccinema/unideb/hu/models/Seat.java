package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@NamedQueries({
        @NamedQuery(name = "Seat.GET_ALL", query = "SELECT s FROM Seat s")
})
public class Seat implements IEntity {

    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    private boolean isAvailable;

    @JoinColumn(name = "theater", referencedColumnName = "theater_id")
    @ManyToOne(optional = false)
    private Theater theater;

    public Seat() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowNumber() {
        return this.seatNumber / this.theater.getRowsCapacity(); // TODO atgondolni, sok koffeint miatt nem megy
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}