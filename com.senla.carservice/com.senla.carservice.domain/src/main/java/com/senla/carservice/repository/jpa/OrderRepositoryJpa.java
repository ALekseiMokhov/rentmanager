package com.senla.carservice.repository.jpa;


import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderRepositoryJpa implements IOrderRepository {
    private final EntityManager em = JpaUtil.getEntityManager();;
    private final CriteriaBuilder builder = em.getCriteriaBuilder();
    private final CriteriaQuery <Order> criteriaQuery = builder.createQuery(Order.class);
    private final Root <Order> root = criteriaQuery.from( Order.class );
    private final CriteriaDelete <Order> criteriaDeleteQuery = this.builder.createCriteriaDelete( Order.class ) ;
    private final Root<Order>deleteRoot = criteriaDeleteQuery.from( Order.class )  ;

    public Order findById(UUID id) {
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
        em.getTransaction().begin();
        List <Order> res = new ArrayList <>();
        try {
            res.addAll( em.createQuery(this.criteriaQuery  ).getResultList() );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + "FROM METHOD FIND ALL()" );
        }
        return res;

    }


    public void delete(UUID id) {
        em.getTransaction().begin();
        try {
            this.criteriaDeleteQuery.where( this.builder.equal( this.deleteRoot.get( "id" ),id ) );
            int result = em.createQuery( this.criteriaDeleteQuery ).executeUpdate();
            System.out.println("entities deleted: " + result);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + "FROM DELETE METHOD" );
        }

    }


    public void save(Order order) {
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
