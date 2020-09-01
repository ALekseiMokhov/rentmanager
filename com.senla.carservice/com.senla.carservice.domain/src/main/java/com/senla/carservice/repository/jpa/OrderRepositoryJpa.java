package com.senla.carservice.repository.jpa;


import com.senla.carservice.entity.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderRepositoryJpa implements IOrderRepository {
    private EntityManager em;


    public Order findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Order order = null;
        try {
            order = em.find( Order.class, id );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + "FROM FIND BY ID METHOD" );
        }
        return order;

    }


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
            log.error( e.getMessage() + "FROM METHOD FIND ALL()" );
        }
        return res;

    }


    public void delete(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from Order o where o.id=:id" );
            int countOfDeleted = query.setParameter( "id", id ).executeUpdate();
            log.info( countOfDeleted + "order were deleted" );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + "FROM DELETE METHOD" );
        }

    }


    public void save(Order order) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( order );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + " FROM SAVE METHOD" );
        }
    }
}
