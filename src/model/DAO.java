package model;

import java.util.List;

public interface DAO<T>
{
    T add(T o);
    boolean remove(T o);
    boolean update(T o);
    List<T> getAll();
    T getByID(int id);
}
