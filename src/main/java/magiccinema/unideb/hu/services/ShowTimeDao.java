package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.services.interfaces.dao.IShowTimeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShowTimeDao implements IShowTimeDao {

    protected static Logger logger = LoggerFactory.getLogger(ShowTimeDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public ShowTimeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("ShowTime Dao created.");
    }

    @Override
    public List<ShowTime> getAll() {
        return this.entityManager.createNamedQuery("ShowTime.GET_ALL").getResultList();
    }

    @Override
    public ShowTime getById(int id) {
        return this.entityManager.find(ShowTime.class, id);
    }

    @Override
    public void modify(ShowTime entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void remove(ShowTime entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(ShowTime entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "ShowTimeDao";
    }
}
