package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Theater;
import magiccinema.unideb.hu.services.interfaces.dao.ITheaterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class TheaterDao implements ITheaterDao {

    protected static Logger logger = LoggerFactory.getLogger(TheaterDao.class);

    private final EntityManager entityManager;

    public TheaterDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Theater Dao created.");
    }

    @Override
    public List<Theater> getAll() {
        return null;
    }

    @Override
    public Theater getById(int id) {
        return null;
    }

    @Override
    public void modify(Theater entity) {

    }

    @Override
    public void remove(Theater entity) {

    }

    @Override
    public void add(Theater entity) {

    }

    @Override
    public String getName() {
        return "TheaterDao";
    }
}
