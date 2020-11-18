package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.Vehicle;

import java.util.List;

@Mapper(componentModel = "spring",uses = GeometryConverter.class)
public interface VehicleMapper {

    VehicleDto toDto(Vehicle vehicle);

    Vehicle fromDto(VehicleDto dto);


    List <Vehicle> listFromDto(List <VehicleDto> list);

    List <VehicleDto> listToDto(List <Vehicle> list);
}
