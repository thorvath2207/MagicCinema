package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.App;
import magiccinema.unideb.hu.models.Genre;
import magiccinema.unideb.hu.services.interfaces.IGenreDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GenreDao implements IGenreDao {

    protected static Logger logger = LoggerFactory.getLogger(App.class);

    private final EntityManager entityManager;

    public GenreDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cinema-db");
        this.entityManager = factory.createEntityManager();
        logger.trace("Genre Dao created.");
    }

    @Override
    public String getName() {
        return "GenreDao";
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createNamedQuery("Genre.GET_ALL").getResultList();
    }

    @Override
    public Genre getById(int id) {
        return null;
    }

    @Override
    public void modify(Genre entity) {

    }

    @Override
    public void remove(Genre entity) {

    }

    @Override
    public void add(Genre entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}