package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.GenericMapper;
import ru.rambler.alexeimohov.entities.RentPoint;

@Mapper(componentModel = "spring", uses = GeometryMapper.class)
public interface RentPointMapper extends GenericMapper <RentPoint,RentPointDto> {

}
