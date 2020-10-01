package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.master.AbstractMaster;
import org.springframework.stereotype.Repository;

@Repository
public class MasterJpaRepository extends GenericJpaRepository{
    public MasterJpaRepository() {
        super.setClass( AbstractMaster.class );
    }
}
