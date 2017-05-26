package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "movies")
@NamedQueries({
        @NamedQuery(name = "Movie.GET_ALL", query = "SELECT m FROM Movie m")
})
public class Movie implements IEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "cover_picture", nullable = true)
    private byte[] image;

    @ManyToMany
    private Collection<Actor> actorsCollection;

    @ManyToMany
    private Collection<Genre> genresCollection;

    public Movie() {

    }

    public Movie(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Actor> getActorsCollection() {
        return actorsCollection;
    }

    public void setActorsCollection(Collection<Actor> actorsCollection) {
        this.actorsCollection = actorsCollection;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Collection<Genre> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genre> genres) {
        this.genresCollection = genres;
    }
}
