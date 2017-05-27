package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genre")
@NamedQueries({
        @NamedQuery(name = "Genre.GET_ALL", query = "SELECT g FROM Genre g")
})
public class Genre implements IEntity {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int genreId;

    @Column(name = "genre_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genresCollection")
    private Collection<Movie> moviesCollection;

    public Genre() {
        super();
    }

    public Genre(String name) {
        this.name = name;
    }

    public Collection<Movie> getMoviesCollection() {
        return moviesCollection;
    }

    public void setMoviesCollection(Collection<Movie> moviesCollection) {
        this.moviesCollection = moviesCollection;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        return name != null ? name.equals(genre.name) : genre.name == null;

    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}