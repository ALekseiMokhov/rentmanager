package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.master.AbstractMaster;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.repository.IMasterRepository;
import dependency.injection.annotations.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Qualifier
public class MasterRepositoryJpa implements IMasterRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger( MasterRepositoryJpa.class );
    private EntityManager em;

    @Override
    @Transactional
    public IMaster findById(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        IMaster master = null;
        try {
            master = em.find( AbstractMaster.class, id );
            LOGGER.info( "FOUND SPECIALITY: " + master.getSpeciality() + " ,MASTER'S CLASS IS : " + master.getClass() );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }
        return master;
    }


    @Override
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
            LOGGER.error( e.getMessage() + "FROM METHOD FIND ALL()" );
        }
        return res;
    }

    @Override
    public void delete(UUID id) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from AbstractMaster m where m.id=:p" );
            int countOfDeleted = query.setParameter( "p", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }

    }

    @Override
    public void save(IMaster master) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( master );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + " FROM SAVE METHOD" );
        }

    }

    @Override
    public IMaster getBySpeciality(Speciality speciality) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        IMaster master = null;
        try {
            Query query = em.createQuery( "select m from AbstractMaster m where m.speciality=:s" );
            query.setParameter( "s", speciality );
            master = (IMaster) query.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }
        return master;
    }

    @Override
    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        IMaster master = null;
        try {
            Query query = em.createQuery( "select m from AbstractMaster m where m.speciality=:s" );
            query.setParameter( "s", speciality );
            master = (IMaster) query.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }
        if (!master.getCalendar().isDateBooked( date )) {
            return master;
        }
        throw new NoSuchElementException( "There is no masters of required speciality for the chosen Date!" );
    }

    @Override
    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {

        em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        IMaster master = null;
        try {
            Query query = em.createQuery( "select m from AbstractMaster m where m.speciality=:s and m.fullName=:n" );
            query.setParameter( "s", speciality );
            query.setParameter( "n", name );
            master = (IMaster) query.getSingleResult();
            em.getTransaction().commit();
            return master;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }

        throw new NoSuchElementException( "There is no masters of required speciality or chosen name!" );
    }
}
