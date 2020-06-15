package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.garage.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IPlaceService {
    List <Place> getPlaces();

    List <Place> getFreePlacesForDate(LocalDate date);

    void addPlaces(int i);

    boolean isPlaceSetForDate(Place place, LocalDate date);

    void setPlaceForDate(Place place, LocalDate date);

    void setPlaceFree(Place place, LocalDate date);

    void setPlaceId(Place place, UUID id);

    void savePlace(Place place);

    Place getFreePlace(LocalDate date);

    Place getPlaceById(UUID id);
}
