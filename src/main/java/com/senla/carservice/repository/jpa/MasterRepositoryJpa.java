package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.master.AbstractMaster;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.repository.IMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MasterRepositoryJpa implements IMasterRepository {
    private EntityManager em;


    public IMaster findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        IMaster master = null;
        try {
            master = em.find( AbstractMaster.class, id );
            log.info( "FOUND SPECIALITY: " + master.getSpeciality() + " ,MASTER'S CLASS IS : " + master.getClass() );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() );
        }
        return master;

    }


    public List <IMaster> findAll() {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        List <IMaster> res = new ArrayList <>();
        try {
            Query query = em.createQuery( "select m from AbstractMaster m" );
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
            Query query = em.createQuery( "delete from AbstractMaster m where m.id=:p" );
            int countOfDeleted = query.setParameter( "p", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() );
        }

    }

    public void save(IMaster master) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( master );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error( e.getMessage() + " FROM SAVE METHOD" );
        }

    }


}
