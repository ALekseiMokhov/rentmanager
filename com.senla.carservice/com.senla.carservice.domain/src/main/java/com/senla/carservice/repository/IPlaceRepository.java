package com.senla.carservice.repository;


import com.senla.carservice.entity.garage.Place;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Qualifier("placeRepositoryJpa")
public interface IPlaceRepository {
    Place findById(UUID id);

    List <Place> findAll();

    void delete(UUID id);

    void save(Place place);

}
