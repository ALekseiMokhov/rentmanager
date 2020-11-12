package ru.rambler.alexeimohov.dto.mappers;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.entities.RentPoint;

@Mapper(componentModel = "spring", uses = GeometryMapper.class)
public interface RentPointMapper extends  GenericMapper<RentPoint,RentPointDto>{

}
