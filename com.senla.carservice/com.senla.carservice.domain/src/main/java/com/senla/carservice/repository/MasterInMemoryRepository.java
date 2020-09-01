package com.senla.carservice.repository;


import com.senla.carservice.entity.master.IMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Deprecated
public class MasterInMemoryRepository implements IMasterRepository {
    private final List <IMaster> masters = new ArrayList <>();

    public MasterInMemoryRepository() {
    }

    @Override
    public IMaster findById(UUID id) {
        return this.masters
                .stream()
                .filter( master -> master.getId().equals( id ) )
                .findFirst()
                .get();
    }

    @Override
    public List <IMaster> findAll() {
        return this.masters;
    }

    @Override
    public void delete(UUID id) {
        if (!this.masters
                .contains( findById( id ) )) {
            throw new NoSuchElementException( "Repository doesn't contain master with the provided id!" );
        }
        this.masters.remove( this.findById( id ) );
    }

    @Override
    public void save(IMaster master) {
        if (!this.masters.contains( master )) {
            this.masters.add( master );
        } else {
            this.masters.set( this.masters.indexOf( master ), master );
        }
    }

}

