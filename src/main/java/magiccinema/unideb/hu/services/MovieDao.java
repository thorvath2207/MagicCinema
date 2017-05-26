package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.services.interfaces.dao.IMovieDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieDao implements IMovieDao{

    protected static Logger logger = LoggerFactory.getLogger(MovieDao.class);

    private final EntityManager entityManager;

    public MovieDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Movie Dao created.");
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public Movie getById(int id) {
        return null;
    }

    @Override
    public void modify(Movie entity) {

    }

    @Override
    public void remove(Movie entity) {

    }

    @Override
    public void add(Movie entity) {

    }

    @Override
    public String getName() {
        return "MovieDao";
    }
}
