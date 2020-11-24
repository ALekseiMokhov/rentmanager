package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.VehicleDto;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IVehicleService {
    void remove(Long id);

    void saveOrUpdate(VehicleDto dto);

    void setDateForBooking(Long id, LocalDate date);

    VehicleDto getById(Long id);

    List <VehicleDto> getAll();
    
    List <VehicleDto> getAllChildish();

    List <VehicleDto> getAllMuscular();

    List <VehicleDto> getAllFromPoint(Long id);

    List <VehicleDto> getAllFreeFromPoint(Long id, LocalDate localDate);
}
