package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.repository.IMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;


public class MasterRepositoryJpa /*implements IMasterRepository*/ {
    private final static Logger LOGGER = LoggerFactory.getLogger( MasterRepositoryJpa.class );
   /* private EntityManager em;*/


    public IMaster findById(UUID id) {
     /*   em = JpaUtil.getEntityManager();
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
        return master;*/
        return null;
    }



    public List <IMaster> findAll() {
       /* em = JpaUtil.getEntityManager();
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
        return res;*/
        return null;
    }


    public void delete(UUID id) {
       /* em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery( "delete from AbstractMaster m where m.id=:p" );
            int countOfDeleted = query.setParameter( "p", id ).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() );
        }*/

    }

    public void save(IMaster master) {
        /*em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge( master );
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error( e.getMessage() + " FROM SAVE METHOD" );
        }*/

    }


}
