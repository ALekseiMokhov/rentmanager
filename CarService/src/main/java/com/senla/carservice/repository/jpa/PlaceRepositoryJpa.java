package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import dependency.injection.annotations.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PlaceRepositoryJpa implements IPlaceRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger( PlaceRepositoryJpa.class );
    private EntityManager em;

    @Override
    public Place findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Place place = null;
        try {
            place = em.find( Place.class, id );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }
        return place;
    }


    @Override
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
            LOGGER.error( e.getMessage() + "FROM METHOD FIND ALL()" );
        }
        return res;
    }

    @Override
    public void delete(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from Place p where p.id=:p" );
            int countOfDeleted = query.setParameter( "p", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }

    }

    @Override
    public void save(Place place) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( place );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }

    }

}
