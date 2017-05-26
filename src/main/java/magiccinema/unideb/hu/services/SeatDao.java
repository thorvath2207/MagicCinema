package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Seat;
import magiccinema.unideb.hu.services.interfaces.dao.ISeatDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SeatDao implements ISeatDao {
    protected static Logger logger = LoggerFactory.getLogger(SeatDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public SeatDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Seat Dao created.");
    }

    @Override
    public List<Seat> getAll() {
        return this.entityManager.createNamedQuery("Seat.GET_ALL").getResultList();
    }

    @Override
    public Seat getById(int id) {
        return this.entityManager.find(Seat.class, id);
    }

    @Override
    public void modify(Seat entity) {

    }

    @Override
    public void remove(Seat entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Seat entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "SeatDao";
    }
}
