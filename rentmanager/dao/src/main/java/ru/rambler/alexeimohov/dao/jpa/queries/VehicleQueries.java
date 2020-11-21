package ru.rambler.alexeimohov.dao.jpa.queries;

public interface VehicleQueries {
    String SELECT_ALL_HUMAN_POWERED_VEHICLES = "select v from Vehicle v where v.isHumanPowered = true";
    String SELECT_ALL_CHILDISH_VEHICLES = "select v from Vehicle v where v.isChildish = true ";
    String SELECT_ALL_FREE_VEHICLES_BY_POINT = """
                        select v from Vehicle v where v.rentPoint.id =:id and
                        not exists (select d from v.bookedDates d where d=:date)
                        """ ;
    String SELECT_ALL_VEHICLES = "select v from Vehicle v where v.rentPoint.id =:id ";
}
