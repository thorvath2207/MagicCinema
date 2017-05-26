package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Actor;
import magiccinema.unideb.hu.services.interfaces.dao.IActorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ActorDao implements IActorDao {

    protected static Logger logger = LoggerFactory.getLogger(ActorDao.class);

    @PersistenceContext
    private final EntityManager entityManager;


    public ActorDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        logger.trace("Actor Dao created.");
    }

    @Override
    public List<Actor> getAll() {
        return this.entityManager.createNamedQuery("Actor.GET_ALL").getResultList();
    }

    @Override
    public Actor getById(int id) {
            return this.entityManager.find(Actor.class, id);
    }

    @Override
    public void modify(Actor entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void remove(Actor entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void add(Actor entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public String getName() {
        return "ActorDao";
    }
}
