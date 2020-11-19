package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.entities.Vehicle;

import java.util.List;

public interface IVehicleService {
    void remove(Long id);

    void saveOrUpdate(VehicleDto dto);

    VehicleDto getById(Long id);

    List <VehicleDto> getAll();
    
    List <VehicleDto> getAllChildish();

    List <VehicleDto> getAllMuscular();

    List <VehicleDto> getAllFromPoint(Long id);

    List <VehicleDto> getAllFreeFromPoint(Long id);
}
