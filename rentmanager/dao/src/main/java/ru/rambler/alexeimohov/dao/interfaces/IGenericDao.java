package ru.rambler.alexeimohov.dao.interfaces;

import java.util.List;

public interface IGenericDao<T> {

    T findById(Long id);

    void remove(Long id);

    void save(T object);

    List <T> findAll();

    void update(T object);
}
