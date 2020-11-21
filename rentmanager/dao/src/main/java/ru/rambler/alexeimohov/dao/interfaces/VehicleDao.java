package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface VehicleDao extends IGenericDao <Vehicle> {
    void setDateBooked(Long id, LocalDate date);

    boolean isBooked(Long id,LocalDate date);

    Set <LocalDate> getBookedDates(Long id);

    List <Vehicle> findAllChildish();

    List <Vehicle> findAllMuscular();

    List <Vehicle> findAllFromPoint(Long id);

    List <Vehicle> findAllFreeFromPoint(Long id, LocalDate date);
}
