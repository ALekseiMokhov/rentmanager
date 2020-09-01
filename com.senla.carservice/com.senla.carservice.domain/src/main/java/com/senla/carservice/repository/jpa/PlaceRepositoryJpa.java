package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PlaceRepositoryJpa implements IPlaceRepository {
    private EntityManager em;


    public Place findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Place place = null;
        try {
            place = em.find( Place.class, id );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() );
        }
        return place;

    }


    public List <Place> findAll() {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        List <Place> res = new ArrayList <>();
        try {
            Query query = em.createQuery( "select p from Place p" );
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
            Query query = em.createQuery( "delete from Place p where p.id=:p" );
            int countOfDeleted = query.setParameter( "p", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() );
        }

    }


    public void save(Place place) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( place );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() );
        }
    }

}
