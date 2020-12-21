package ru.rambler.alexeimohov.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/*
 * Sharing @Persistence context among all Dao implementations*/

public abstract class GenericDaoJpa {
    @PersistenceContext
    EntityManager entityManager;
}
