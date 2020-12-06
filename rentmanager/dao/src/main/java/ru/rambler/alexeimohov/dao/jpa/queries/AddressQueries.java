package ru.rambler.alexeimohov.dao.jpa.queries;

public interface AddressQueries {
    String FIND_ALL_ADDRESSES = " select a from Address a";
    String FIND_ALL_SORTED_BY_CITY = " select a from address a where a.city =:city";
}
