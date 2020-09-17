package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IPlaceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceJpaRepository extends GenericJpaRepository  {
    public PlaceJpaRepository() {
        super.setClass( Place.class );
    }
}
