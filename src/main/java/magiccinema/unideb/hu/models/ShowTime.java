package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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
    private Date time;

    @JoinColumn(name = "theater", referencedColumnName = "theater_id")
    @ManyToOne(optional = false)
    private Theater theater;

    @JoinColumn(name = "movie", referencedColumnName = "movie_id")
    @ManyToOne(optional = false)
    private Movie movie;

    @OneToMany(mappedBy = "showTime")
    private Collection<Ticket> ticketCollection;

    public ShowTime() {
        super();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}
