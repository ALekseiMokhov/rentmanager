package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.garage.Place;

import java.util.List;
import java.util.UUID;

public interface IPlaceRepository {
    Place findById(UUID id);
    List <Place> findAll();
    void delete(UUID id);
    void save(Place place);
}
