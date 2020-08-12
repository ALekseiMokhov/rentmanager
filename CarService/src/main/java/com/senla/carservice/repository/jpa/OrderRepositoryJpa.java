package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import dependency.injection.annotations.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Qualifier
public class OrderRepositoryJpa implements IOrderRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger( OrderRepositoryJpa.class );
    private EntityManager em;

    @Override
    public Order findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Order order = null;
        try {
            order = em.find( Order.class, id );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + "FROM FIND BY ID METHOD" );
        }
        return order;
    }

    @Override
    public List <Order> findAll() {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        List <Order> res = new ArrayList <>();
        try {
            Query query = em.createQuery( "select o from Order o" );
            res = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + "FROM METHOD FIND ALL()" );
        }
        return res;
    }

    @Override
    public void delete(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from Order o where o.id=:id" );
            int countOfDeleted = query.setParameter( "id", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + "FROM DELETE METHOD" );
        }


    }

    @Override
    public void save(Order order) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( order );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + " FROM SAVE METHOD" );
        }
    }
}
