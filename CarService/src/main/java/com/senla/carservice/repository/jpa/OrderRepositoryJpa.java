package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;


public class OrderRepositoryJpa /*implements IOrderRepository*/ {
    private final static Logger LOGGER = LoggerFactory.getLogger( OrderRepositoryJpa.class );
    /*private EntityManager em;*/


    public Order findById(UUID id) {
       /* em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Order order = null;
        try {
            order = em.find( Order.class, id );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + "FROM FIND BY ID METHOD" );
        }
        return order;*/
        return null;
    }


    public List <Order> findAll() {
        /*em = JpaUtil.getEntityManager();
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
        return res;*/
        return null;
    }


    public void delete(UUID id) {
      /*  em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from Order o where o.id=:id" );
            int countOfDeleted = query.setParameter( "id", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + "FROM DELETE METHOD" );
        }
*/

    }


    public void save(Order order) {
        /*em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( order );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + " FROM SAVE METHOD" );
        }*/
    }
}
