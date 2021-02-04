package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.jpa.queries.CardQueries;
import ru.rambler.alexeimohov.dao.jpa.queries.OrderQueries;
import ru.rambler.alexeimohov.dao.jpa.queries.VehicleQueries;
import ru.rambler.alexeimohov.entities.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Order DAO JPA implementation. Uses JPQL queries to sort data*/
@Repository
public class OrderDaoJpaImpl extends GenericDaoJpa implements OrderDao {
    @Override
    public Order findById(long id) {
        return ((Order) entityManager.createQuery(OrderQueries.FIND_ORDER_BY_ID)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public double getAvailableFunds(long creditCardNumber) {
        double actualFunds = (double) entityManager.createQuery(CardQueries.FIND_AVAILABLE_FUNDS)
                .setParameter("number", creditCardNumber)
                .getSingleResult();
        return actualFunds;
    }

    @Override
    public Set<Date> getBookedDatesOfChosenVehicle(long id) {
        Set<Date> retrieved = new HashSet<>();
        retrieved.addAll(entityManager.createNativeQuery(VehicleQueries.GET_BOOKED_DATES_NATIVE_QUERY)
                .setParameter(1, id)
                .getResultList());
        return retrieved;
    }

    @Override
    public void remove(long id) {
        entityManager.remove(this.findById(id));
    }

    @Override
    public void save(Order object) {
        entityManager.persist(object);
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery(OrderQueries.FIND_ALL_ORDERS)
                .getResultList();
    }

    @Override
    public void update(Order object) {
        entityManager.merge(object);
    }
}
