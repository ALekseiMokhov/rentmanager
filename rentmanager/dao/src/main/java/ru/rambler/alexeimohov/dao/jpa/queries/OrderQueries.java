package ru.rambler.alexeimohov.dao.jpa.queries;

public interface OrderQueries {
    String FIND_ORDER_BY_ID = "select o from Order o where o.id =:id";
    String FIND_ALL_ORDERS = "select o from Order o ";
}
