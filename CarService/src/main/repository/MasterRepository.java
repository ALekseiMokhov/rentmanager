package main.repository;

import main.entities.master.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MasterRepository implements Repository <Master> {
    private final List <Master> masters = new ArrayList <>();

    @Override
    public Master findById(UUID id) {
        return this.masters.stream().filter( master -> master.getId().equals( id ) ).findFirst().get();
    }

    @Override
    public List <Master> findAll() {
        return this.masters;
    }

    @Override
    public void delete(UUID id) {
        this.masters.removeIf( master -> master.getId().equals( id ) );

    }

    @Override
    public void save(Master master) {
        if (!this.masters.contains( master )) {
            this.masters.add( master );
        } else {
            this.masters.set( this.masters.indexOf( master ), master );
        }
    }


}

