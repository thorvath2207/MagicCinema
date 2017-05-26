package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Table(name = "genre")
@NamedQuery(name = "Genre.GET_ALL", query = "SELECT g FROM Genre g")
public class Genre implements IEntity {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int genreId;

    @Column(name = "genre_name", nullable = false)
    private String name;

    public Genre() {
        super();
    }

    public Genre(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return this.genreId;
    }

    public void setGenreId(int id) {
        this.genreId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}