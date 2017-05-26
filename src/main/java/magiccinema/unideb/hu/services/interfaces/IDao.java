package magiccinema.unideb.hu.services.interfaces;

import java.util.List;

public interface IDao<T>  extends IService {
    List<T> getAll();

    T getById(int id);

    void modify(T entity);

    void remove(T entity);

    void add(T entity);
}
