package ru.rambler.alexeimohov.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/*
 * Sharing @Persistence context among Dao implementations*/

public abstract class GenericJpaDao {
    @PersistenceContext
    EntityManager entityManager;
}
