package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Genre;
import magiccinema.unideb.hu.services.interfaces.dao.IGenreDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenreDao implements IGenreDao {

    protected static Logger logger = LoggerFactory.getLogger(GenreDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Genre Dao created.");
    }

    @Override
    public String getName() {
        return "GenreDao";
    }

    @Override
    public List<Genre> getAll() {
        return this.entityManager.createNamedQuery("Genre.GET_ALL").getResultList();
    }

    @Override
    public Genre getById(int id) {
       return this.entityManager.find(Genre.class, id);
    }

    @Override
    public void modify(Genre entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void remove(Genre entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Genre entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}