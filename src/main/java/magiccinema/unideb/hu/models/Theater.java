package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "theater")
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
}