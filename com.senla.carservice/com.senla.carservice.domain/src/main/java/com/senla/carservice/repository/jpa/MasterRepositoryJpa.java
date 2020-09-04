package com.senla.carservice.repository.jpa;


import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.IMaster;
import com.senla.carservice.repository.IMasterRepository;
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
public class MasterRepositoryJpa implements IMasterRepository {
    private final EntityManager em = JpaUtil.getEntityManager();;
    private final CriteriaBuilder builder = em.getCriteriaBuilder();
    private final CriteriaQuery <AbstractMaster> criteriaQuery = builder.createQuery(AbstractMaster.class);
    private final Root <AbstractMaster> root = criteriaQuery.from( AbstractMaster.class );
    private final CriteriaDelete <AbstractMaster> criteriaDeleteQuery = this.builder.createCriteriaDelete( AbstractMaster.class ) ;
    private final Root<AbstractMaster>deleteRoot = criteriaDeleteQuery.from( AbstractMaster.class )  ;

    public IMaster findById(UUID id) {
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
        em.getTransaction().begin();
        List <IMaster> res = new ArrayList <>();
        try {

             res.addAll( em.createQuery( this.criteriaQuery ).getResultList()  );
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
            log.error( e.getMessage() +"FROM DELETE METHOD");
        }

    }

    public void save(IMaster master) {
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
