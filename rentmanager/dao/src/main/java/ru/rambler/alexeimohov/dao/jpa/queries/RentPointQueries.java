package ru.rambler.alexeimohov.dao.jpa.queries;

public interface RentPointQueries {
    String FIND_ALL_POINTS = "select p from RentPoint p";
    String FIND_POINT_BY_COORDINATE = "select p from RentPoint p where p.coordinate =:coordinate";
}
