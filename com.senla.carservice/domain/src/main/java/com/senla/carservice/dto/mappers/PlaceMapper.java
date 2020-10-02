package com.senla.carservice.dto.mappers;

import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.entity.garage.Place;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UuidMapper.class)
public interface PlaceMapper {
    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    PlaceDto placeToDto(Place place);

    Place dtoToPlace(PlaceDto placeDto);
}
