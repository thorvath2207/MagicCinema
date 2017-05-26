package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Ticket;
import magiccinema.unideb.hu.services.interfaces.dao.ITicketDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class TicketDao implements ITicketDao {

    protected static Logger logger = LoggerFactory.getLogger(TicketDao.class);

    private final EntityManager entityManager;

    public TicketDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Ticket Dao created.");
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Ticket getById(int id) {
        return null;
    }

    @Override
    public void modify(Ticket entity) {

    }

    @Override
    public void remove(Ticket entity) {

    }

    @Override
    public void add(Ticket entity) {

    }

    @Override
    public String getName() {
        return "TicketDao";
    }
}
