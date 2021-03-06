package ru.rambler.alexeimohov.dao.jpa.queries;

public interface VehicleQueries {
    String SELECT_ALL_HUMAN_POWERED_VEHICLES = "select v from Vehicle v where v.isHumanPowered = true";

    String SELECT_ALL_CHILDISH_VEHICLES = "select v from Vehicle v where v.isChildish = true ";

    String SELECT_ALL_FREE_VEHICLES_BY_POINT = " select v from Vehicle v where v.rentPoint.id =:id and " +
            "not exists (select d from v.bookedDates d where d=:date)";

    String SELECT_ALL_VEHICLES = "select v from Vehicle v where v.rentPoint.id =:id";

    String SELECT_ALL_BOOKED_DATES = "select v from Vehicle v join fetch v.bookedDates where v.id=:id";

    String GET_BOOKED_DATES_NATIVE_QUERY = "select booked_dates from test.vehicle_booked_dates where id = ?1";
}
