package com.senla.carservice.repository.interfaces;


import com.senla.carservice.entity.garage.Place;

import java.util.List;
import java.util.UUID;


public interface IPlaceRepository extends IGenericRepository {
    Place getById(UUID id);

    List<Place> findAll();

    void delete(UUID id);

    void save(Place place);

}
