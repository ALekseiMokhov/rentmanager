package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.jpa.queries.OrderQueries;
import ru.rambler.alexeimohov.entities.Order;

import java.util.List;

@Repository
public class OrderDaoJpaImpl extends GenericJpaDao implements OrderDao {
    @Override
    public Order findById(Long id) {
        return ((Order) entityManager.createQuery( OrderQueries.FIND_ORDER_BY_ID )
                .setParameter( "id", id )
                .getSingleResult());
    }

    @Override
    public void remove(Long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Order object) {
        entityManager.persist( object );
    }

    @Override
    public List <Order> findAll() {
        return entityManager.createQuery( OrderQueries.FIND_ALL_ORDERS )
                .getResultList();
    }

    @Override
    public void update(Order object) {
        entityManager.merge( object );
    }
}
