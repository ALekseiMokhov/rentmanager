package com.senla.carservice.repository.jpa;

import com.senla.carservice.repository.interfaces.IGenericRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;


public abstract class GenericJpaRepository<T> implements IGenericRepository {
    @PersistenceContext
    EntityManager entityManager;
    private Class <T> clazz;

    public void setClass(Class clazz) {
        this.clazz = clazz;
    }

    public T getById(UUID id) {
        return entityManager.find( clazz, id );
    }

    public List <T> findAll() {
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public void delete(UUID id) {
        T t = getById( id );
        entityManager.remove( t );
    }

    public void save(Object entity) {
        entityManager.persist( entity );
    }

    public T update(Object entity) {
        return (T) entityManager.merge( entity );
    }


}
