package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PlaceRepositoryJpa implements IPlaceRepository {
    private final EntityManager em = JpaUtil.getEntityManager();;
    private final CriteriaBuilder builder = em.getCriteriaBuilder();
    private final CriteriaQuery <Place> criteriaQuery = builder.createQuery(Place.class);
    private final Root <Place> root = criteriaQuery.from( Place.class );
    private final CriteriaDelete <Place> criteriaDeleteQuery = this.builder.createCriteriaDelete( Place.class ) ;
    private final Root<Place>deleteRoot = criteriaDeleteQuery.from( Place.class )  ;

    public Place findById(UUID id) {
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
        em.getTransaction().begin();
        List <Place> res = new ArrayList <>();
        try {

            res =  em.createQuery( this.criteriaQuery ) .getResultList();
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
            this.criteriaDeleteQuery.where( this.builder.equal( this.deleteRoot.get( "id" ),id));
            int result = em.createQuery( this.criteriaDeleteQuery ).executeUpdate();
            System.out.println("entities deleted: " + result);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage()  );
            e.printStackTrace();
        }

    }


    public void save(Place place) {
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
