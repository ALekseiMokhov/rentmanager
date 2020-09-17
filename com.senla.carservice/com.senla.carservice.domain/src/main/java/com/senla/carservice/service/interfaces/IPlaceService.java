package com.senla.carservice.service.interfaces;


import com.senla.carservice.entity.garage.Place;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service

public interface IPlaceService {
    List <Place> getPlaces();

    List <Place> getFreePlacesForDate(LocalDate date);

    void addPlaces(int i);

    boolean isPlaceSetForDate(UUID id, LocalDate date);

    void setPlaceForDate(UUID id, LocalDate date);

    void setPlaceFree(UUID id, LocalDate date);

    void setPlaceId(UUID current, UUID newId);

    void mergePlace(Place place);

    boolean isPresent(UUID id);

    UUID addPlace();

    Place getFreePlace(LocalDate date);

    Place getPlaceById(UUID id);

    void loadFromCsv();

    void exportToCsv();

    void loadPlacesFromJson();

    void exportPlacesToJson();

    void deletePlace(UUID id);

    void savePlace(Place place);
}
