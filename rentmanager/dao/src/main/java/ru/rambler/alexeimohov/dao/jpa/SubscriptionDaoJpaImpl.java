package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.SubscriptionDao;
import ru.rambler.alexeimohov.dao.jpa.queries.SubscriptionQueries;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;

import java.util.List;

/* Subscription DAO JPA implementation. Uses JPQL queries to sort data*/
@Repository("subscriptionDao")
public class SubscriptionDaoJpaImpl extends GenericDaoJpa implements SubscriptionDao {

    @Override
    public Subscription findById(long id) {
        return entityManager.find(Subscription.class, id);
    }

    @Override
    public User getSubscribeHolder(long id) {


        return (User) entityManager.createNativeQuery(SubscriptionQueries.FIND_USER_FOR_SUBSCRIPTION, User.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public void remove(long id) {
        entityManager.remove(this.findById(id));
    }

    @Override
    public void save(Subscription object) {
        entityManager.merge(object);
    }


    @Override
    public List<Subscription> findAll() {
        return entityManager.createQuery(SubscriptionQueries.FIND_ALL_SUBSCRIPTION, Subscription.class).getResultList();
    }

    @Override
    public void update(Subscription object) {
        entityManager.merge(object);
    }

    public boolean isExpired(long id) {
        return entityManager.find(Subscription.class, id).isExpired();
    }
}
