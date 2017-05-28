package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Reservation;
import magiccinema.unideb.hu.services.interfaces.dao.IReservationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReservationDao implements IReservationDao {

    protected static Logger logger = LoggerFactory.getLogger(ReservationDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public ReservationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Reservation Dao created.");
    }

    @Override
    public List<Reservation> getAll() {
        return this.entityManager.createNamedQuery("Reservation.GET_ALL").getResultList();
    }

    @Override
    public Reservation getById(int id) {
        return this.entityManager.find(Reservation.class, id);
    }

    @Override
    public void modify(Reservation entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void remove(Reservation entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Reservation entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }


    @Override
    public String getName() {
        return "ReservationDao";
    }
}
