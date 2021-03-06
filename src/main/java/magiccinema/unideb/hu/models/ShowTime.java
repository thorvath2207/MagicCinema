package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "showtime")
@NamedQueries({
        @NamedQuery(name = "ShowTime.GET_ALL", query = "SELECT s FROM ShowTime s")
})
public class ShowTime implements IEntity {
    @Id
    @Column(name = "showtime_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "startTime", nullable = false)
    private LocalDateTime time;

    @JoinColumn(name = "theater", referencedColumnName = "theater_id")
    @ManyToOne(optional = false)
    private Theater theater;

    @JoinColumn(name = "movie", referencedColumnName = "movie_id")
    @ManyToOne(optional = false)
    private Movie movie;

    @OneToMany(mappedBy = "showTime")
    private Collection<Ticket> ticketCollection;

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public ShowTime() {
        super();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowTime showTime = (ShowTime) o;

        if (id != showTime.id) return false;
        return time != null ? time.equals(showTime.time) : showTime.time == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
