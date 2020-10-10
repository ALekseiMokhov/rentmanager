package com.senla.carservice.repository.interfaces;


import com.senla.carservice.entity.master.Master;

import java.util.List;
import java.util.UUID;

public interface IMasterRepository extends IGenericRepository {
    Master getById(UUID id);

    List<Master> findAll();

    void delete(UUID id);

    void save(Master master);

}
