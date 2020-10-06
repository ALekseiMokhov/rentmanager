package com.senla.carservice.repository.interfaces;


import com.senla.carservice.entity.master.AbstractMaster;

import java.util.List;
import java.util.UUID;

public interface IMasterRepository extends IGenericRepository {
    AbstractMaster getById(UUID id);

    List<AbstractMaster> findAll();

    void delete(UUID id);

    void save(AbstractMaster master);

}
