package com.senla.carservice.repository.interfaces;

import java.util.List;
import java.util.UUID;

public interface IGenericRepository<T> {

    void setClass(Class <T> clazzToSet);

    T getById(UUID id);

    List <T> findAll();

    void save(T entity);

    T update(T entity);

    void delete(UUID id);


}
