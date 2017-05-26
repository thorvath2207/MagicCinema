package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Ticket;
import magiccinema.unideb.hu.services.interfaces.dao.ITicketDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TicketDao implements ITicketDao {

    protected static Logger logger = LoggerFactory.getLogger(TicketDao.class);

    @PersistenceContext
    private final EntityManager entityManager;

    public TicketDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Ticket Dao created.");
    }

    @Override
    public List<Ticket> getAll() {
        return this.entityManager.createNamedQuery("Ticket.GET_ALL").getResultList();
    }

    @Override
    public Ticket getById(int id) {
        return this.entityManager.find(Ticket.class, id);
    }

    @Override
    public void modify(Ticket entity) {

    }

    @Override
    public void remove(Ticket entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Ticket entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "TicketDao";
    }
}
