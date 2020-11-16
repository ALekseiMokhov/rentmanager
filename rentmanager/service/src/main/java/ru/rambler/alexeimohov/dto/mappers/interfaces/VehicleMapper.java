package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.entities.Vehicle;
@Mapper(componentModel = "spring")
public interface VehicleMapper extends GenericMapper<Vehicle, VehicleDto> {
    String pointToString(RentPoint rentPoint);
    RentPoint rentPointFromDto(String dto);
}
