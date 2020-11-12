package ru.rambler.alexeimohov.dao.jpa.queries;

public interface VehicleQueries {
    String SELECT_ALL_HUMAN_POWERED_VEHICLES = "select v from Vehicle v where v.is_human_powered = true";
    String SELECT_ALL_CHILDISH_VEHICLES = "select v from Vehicle v where v.is_childish = true";
    String SELECT_ALL_VEHICLES = "select v from Vehicle v ";
}
