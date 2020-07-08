package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.garage.Place;
import dependency.injection.annotations.components.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface IPlaceRepository {
    Place findById(UUID id);

    boolean isPresent(UUID id);

    List <Place> findAll();

    void delete(UUID id);

    void save(Place place);
}
