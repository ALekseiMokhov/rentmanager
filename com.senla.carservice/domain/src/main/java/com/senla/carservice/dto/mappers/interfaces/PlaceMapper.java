package com.senla.carservice.dto.mappers.interfaces;

import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.dto.mappers.UuidMapper;
import com.senla.carservice.entity.garage.Place;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = UuidMapper.class, componentModel = "spring")

public interface PlaceMapper {
    /*PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);*/

    PlaceDto placeToDto(Place place);

    Place dtoToPlace(PlaceDto placeDto);

    List<PlaceDto> placesListToDto(List<Place> places);

    List<Place> dtoListToPlaces(List<PlaceDto> dto);
}
