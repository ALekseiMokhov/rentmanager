package com.senla.carservice.repository;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import dependency.injection.annotations.components.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface IMasterRepository {
    IMaster findById(UUID id);

    List <IMaster> findAll();

    void delete(UUID id);

    void save(IMaster master);

}
