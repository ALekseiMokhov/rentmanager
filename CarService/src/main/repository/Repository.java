package main.repository;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
    T findById(UUID id);
    List <T> findAll();
    void delete(UUID id);
    void save(T t) ;
}
