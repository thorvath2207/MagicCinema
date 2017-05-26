package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Theater;
import magiccinema.unideb.hu.services.interfaces.dao.ITheaterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TheaterDao implements ITheaterDao {

    protected static Logger logger = LoggerFactory.getLogger(TheaterDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public TheaterDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Theater Dao created.");
    }

    @Override
    public List<Theater> getAll() {
        return this.entityManager.createNamedQuery("Theater.GET_ALL").getResultList();
    }

    @Override
    public Theater getById(int id) {
        return this.entityManager.find(Theater.class, id);
    }

    @Override
    public void modify(Theater entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void remove(Theater entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Theater entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "TheaterDao";
    }
}
