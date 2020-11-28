package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.RentPoint;

import java.util.List;

@Mapper(componentModel = "spring", uses = GeometryConverter.class)
public interface RentPointMapper  {
    RentPoint fromDto(RentPointDto dto);

    RentPointDto toDto(RentPoint rentPoint);

    List <RentPoint> listFromDto(List <RentPointDto> list);

    List <RentPointDto> listToDto(List <RentPoint> list);
}
