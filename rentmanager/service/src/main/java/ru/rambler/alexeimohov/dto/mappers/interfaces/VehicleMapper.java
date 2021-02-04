package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.dto.mappers.BooleanToStringConverter;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.Vehicle;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GeometryConverter.class, BooleanToStringConverter.class})
public interface VehicleMapper {
    @Mapping(source = "isChildish", target = "isChildish")
    @Mapping(source = "isHumanPowered", target = "isHumanPowered")
    VehicleDto toDto(Vehicle vehicle);

    @InheritInverseConfiguration
    Vehicle fromDto(VehicleDto dto);


    List<Vehicle> listFromDto(List<VehicleDto> list);

    List<VehicleDto> listToDto(List<Vehicle> list);
}
