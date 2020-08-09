package com.senla.carservice.repository;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import dependency.injection.annotations.components.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public interface IMasterRepository {
    IMaster findById(UUID id);

    List <IMaster> findAll();

    void delete(UUID id);

    void save(IMaster master);

    IMaster getBySpeciality(Speciality speciality);

    IMaster getFreeBySpeciality(LocalDate date, Speciality speciality);

    IMaster getByNameAndSpeciality(String name, Speciality speciality);


}
