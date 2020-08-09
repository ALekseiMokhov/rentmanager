package com.senla.carservice.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static  final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hibernate"  ) ;
    public static EntityManagerFactory getEntityManagerFactory(){
      return FACTORY;
    }
    public static EntityManager getEntityManager(){

        return FACTORY.createEntityManager();
    }
}
