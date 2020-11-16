package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.RentPoint;

@Mapper(componentModel = "spring", uses = GeometryConverter.class)
public interface RentPointMapper extends GenericMapper <RentPoint, RentPointDto> {

}
