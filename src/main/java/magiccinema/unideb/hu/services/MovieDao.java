package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.services.interfaces.dao.IMovieDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MovieDao implements IMovieDao {

    protected static Logger logger = LoggerFactory.getLogger(MovieDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public MovieDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Movie Dao created.");
    }

    @Override
    public List<Movie> getAll() {
        return this.entityManager.createNamedQuery("Movie.GET_ALL").getResultList();
    }

    @Override
    public Movie getById(int id) {
        return this.entityManager.find(Movie.class, id);
    }

    @Override
    public void modify(Movie entity) {

    }

    @Override
    public void remove(Movie entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Movie entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "MovieDao";
    }
}
