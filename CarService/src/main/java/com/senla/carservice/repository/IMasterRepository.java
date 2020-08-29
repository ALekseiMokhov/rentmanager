package com.senla.carservice.repository;

import com.senla.carservice.domain.entities.master.IMaster;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Qualifier("masterRepositoryJpa")
public interface IMasterRepository {
    IMaster findById(UUID id);

    List <IMaster> findAll();

    void delete(UUID id);

    void save(IMaster master);

}
