package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, E> {

    E save(E e);

    boolean delete(T id);

    void update(E entity);

    Optional<E> findById(T id);

    List<E> findAll();
}
