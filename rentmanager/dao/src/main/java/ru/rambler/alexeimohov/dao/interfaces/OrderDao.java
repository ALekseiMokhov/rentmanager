package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Order;

import java.time.LocalDate;
import java.util.Set;

public interface OrderDao extends IGenericDao <Order> {
    double getAvailableFunds(long creditCardNumber);

    Set <LocalDate> getBookedDatesOfChosenVehicle(long id);
}
