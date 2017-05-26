package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Seat;
import magiccinema.unideb.hu.services.interfaces.dao.ISeatDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class SeatDao implements ISeatDao {
    protected static Logger logger = LoggerFactory.getLogger(SeatDao.class);

    private final EntityManager entityManager;

    public SeatDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Seat Dao created.");
    }

    @Override
    public List<Seat> getAll() {
        return null;
    }

    @Override
    public Seat getById(int id) {
        return null;
    }

    @Override
    public void modify(Seat entity) {

    }

    @Override
    public void remove(Seat entity) {

    }

    @Override
    public void add(Seat entity) {

    }

    @Override
    public String getName() {
        return "SeatDao";
    }
}
