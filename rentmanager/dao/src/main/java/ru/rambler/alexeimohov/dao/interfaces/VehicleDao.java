package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface VehicleDao extends IGenericDao<Vehicle> {
    void setDateBooked(long id, LocalDate date);

    boolean isBooked(long id, LocalDate date);

    Set<LocalDate> getBookedDates(long id);

    List<Vehicle> findAllChildish();

    List<Vehicle> findAllMuscular();

    List<Vehicle> findAllFromPoint(long id);

    List<Vehicle> findAllFreeFromPoint(long id, LocalDate date);
}
