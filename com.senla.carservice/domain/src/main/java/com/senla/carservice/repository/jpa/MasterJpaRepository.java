package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.master.Master;
import org.springframework.stereotype.Repository;

@Repository
public class MasterJpaRepository extends GenericJpaRepository {
    public MasterJpaRepository() {
        super.setClass(Master.class);
    }
}
