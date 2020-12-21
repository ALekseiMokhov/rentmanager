package ru.rambler.alexeimohov.dao.interfaces;

import java.util.List;
/*
*
* Root DAO interface with basic CRUD methods*/
public interface IGenericDao<T> {

    T findById(long id);

    void remove(long id);

    void save(T object);

    List <T> findAll();

    void update(T object);
}
