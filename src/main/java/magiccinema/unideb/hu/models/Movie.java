package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Entity
@Table(name = "movies")
@NamedQuery(name = "Movie.GET_ALL", query = "SELECT m FROM Movie m")
public class Movie implements IEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;
    private BufferedImage image;

    @ManyToMany(mappedBy = "")
    private ArrayList<Actor> actors;
    private ArrayList<Genre> genres;

    public Movie() {

    }

    public Movie(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }
}
