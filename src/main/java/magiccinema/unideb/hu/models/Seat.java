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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (id != seat.id) return false;
        if (seatNumber != seat.seatNumber) return false;
        return isAvailable == seat.isAvailable;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + seatNumber;
        result = 31 * result + (isAvailable ? 1 : 0);
        return result;
    }
}