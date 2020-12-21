package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

public interface OrderDao extends IGenericDao <Order> {
    double getAvailableFunds(long creditCardNumber);

    Set <Date> getBookedDatesOfChosenVehicle(long id);
}
