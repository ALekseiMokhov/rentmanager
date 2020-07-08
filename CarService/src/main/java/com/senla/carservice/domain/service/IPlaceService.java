package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.garage.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IPlaceService {
    List <Place> getPlaces();

    List <Place> getFreePlacesForDate(LocalDate date);

    void addPlaces(int i);

    boolean isPlaceSetForDate(UUID id, LocalDate date);

    void setPlaceForDate(UUID id, LocalDate date);

    void setPlaceFree(UUID id, LocalDate date);

    void setPlaceId(UUID current, UUID newId);

    void savePlace(UUID id);

    void loadPlace(Place place);

    boolean isPresent(UUID id);

    UUID addPlace();

    Place getFreePlace(LocalDate date);

    Place getPlaceById(UUID id);

    void loadFromCsv();

    void exportToCsv();

    public void loadPlacesFromJson();

    public void exportPlacesToJson();

}
