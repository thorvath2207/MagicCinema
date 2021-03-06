package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "theater")
@NamedQueries({
        @NamedQuery(name = "Theater.GET_ALL", query = "SELECT t FROM Theater t")
})
public class Theater implements IEntity {

    @Id
    @Column(name = "theater_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rows")
    private int rows;

    @Column(name = "rows_capacity")
    private int rowsCapacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theater")
    private Collection<ShowTime> showTimeCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theater")
    private Collection<Seat> seatCollection;

    public Theater() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRowsCapacity() {
        return rowsCapacity;
    }

    public void setRowsCapacity(int rowsCapacity) {
        this.rowsCapacity = rowsCapacity;
    }

    public Collection<ShowTime> getShowTimeCollection() {
        return showTimeCollection;
    }

    public void setShowTimeCollection(Collection<ShowTime> showTimeCollection) {
        this.showTimeCollection = showTimeCollection;
    }

    public Collection<Seat> getSeatCollection() {
        return seatCollection;
    }

    public void setSeatCollection(Collection<Seat> seatCollection) {
        this.seatCollection = seatCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theater theater = (Theater) o;

        if (id != theater.id) return false;
        if (rows != theater.rows) return false;
        return rowsCapacity == theater.rowsCapacity;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rows;
        result = 31 * result + rowsCapacity;
        return result;
    }
}