package com.senla.carservice.service.interfaces;


import com.senla.carservice.dto.PlaceDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service

public interface IPlaceService {
    List<PlaceDto> getPlaceDto();

    List<PlaceDto> getFreePlaceDtoForDate(LocalDate date);

    void addPlaces(int i);

    boolean isPlaceSetForDate(UUID id, LocalDate date);

    void setPlaceForDate(UUID id, LocalDate date);

    void setPlaceFree(UUID id, LocalDate date);

    UUID addPlace();

    PlaceDto getFreePlaceDto(LocalDate date);

    PlaceDto getPlaceById(UUID id);

    void deletePlace(UUID id);

    void savePlace(PlaceDto dto);
}
