package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.services.interfaces.dao.IShowTimeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ShowTimeDao implements IShowTimeDao {

    protected static Logger logger = LoggerFactory.getLogger(ShowTimeDao.class);

    private final EntityManager entityManager;

    public ShowTimeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("ShowTime Dao created.");
    }

    @Override
    public List<ShowTime> getAll() {
        return null;
    }

    @Override
    public ShowTime getById(int id) {
        return null;
    }

    @Override
    public void modify(ShowTime entity) {

    }

    @Override
    public void remove(ShowTime entity) {

    }

    @Override
    public void add(ShowTime entity) {

    }

    @Override
    public String getName() {
        return "ShowTimeDao";
    }
}
