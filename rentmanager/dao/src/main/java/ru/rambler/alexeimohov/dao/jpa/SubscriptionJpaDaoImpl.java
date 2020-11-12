package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.SubscriptionDao;
import ru.rambler.alexeimohov.dao.jpa.queries.SubscriptionQueries;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;

import javax.persistence.Query;
import java.util.List;

@Repository("subscriptionDao")
public class SubscriptionJpaDaoImpl extends GenericJpaDao implements SubscriptionDao {

    @Override
    public Subscription findById(Long id) {
        return entityManager.find( Subscription.class, id );
    }

    @Override
    public User getSubscribeHolder(Long id) {


        return (User) entityManager.createNativeQuery( SubscriptionQueries.FIND_USER_FOR_SUBSCRIPTION, User.class )
                .setParameter( 1, id )
                .getSingleResult();
    }

    @Override
    public void remove(Long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Subscription object) {
        entityManager.persist( object );
        Query query = entityManager.createNativeQuery( "insert into user_subscription values(?,?)" );
        query.setParameter( 1, object.getUser().getId() );
        query.setParameter( 2, object.getId() );
        query.executeUpdate();
    }

    @Override
    public List <Subscription> findAll() {
        return entityManager.createQuery( SubscriptionQueries.FIND_ALL_SUBSCRIPTION, Subscription.class ).getResultList();
    }

    @Override
    public void update(Subscription object) {
        entityManager.merge( object );
    }
}
