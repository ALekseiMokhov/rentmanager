package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Vehicle;

import java.util.List;

public interface VehicleDao extends IGenericDao <Vehicle> {

    List <Vehicle> findAllChildish();

    List <Vehicle> findAllMuscular();

    List <Vehicle> findAllFromPoint(Long id);

    List <Vehicle> findAllFreeFromPoint(Long id);
}
