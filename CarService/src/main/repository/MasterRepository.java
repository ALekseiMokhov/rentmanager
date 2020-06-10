package main.repository;

import main.entities.master.Master;
import main.entities.master.Speciality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class MasterRepository implements Repository <Master> {
    private final List <Master> masters;

    public MasterRepository() {
        this.masters = new ArrayList <>();
    }

    @Override
    public Master findById(UUID id) {
        return this.masters
                .stream()
                .filter( master -> master.getId().equals( id ) )
                .findFirst()
                .get();
    }

    @Override
    public List <Master> findAll() {
        return this.masters;
    }

    @Override
    public void delete(UUID id) {
        if(!this.masters
                .contains( findById( id ) )) {
             throw new IllegalStateException( "Repository doesn't contain master with such id!" );
        }
        this.masters.remove(this.findById( id )  ) ;
    }

    @Override
    public void save(Master master) {
        if (!this.masters.contains( master )) {
            this.masters.add( master );
        } else {
            this.masters.set( this.masters.indexOf( master ), master );
        }
    }
    public Master getBySpeciality(Speciality speciality) {
        for (Master master : masters) {
           if(master.getSpeciality()==speciality){
               return master;
           }
        }
        throw new IllegalStateException( "There is no masters with given speciality" )  ;
    }
    public Master getFreeBySpeciality(LocalDate date,Speciality speciality) {
        for (Master master : masters) {
           if(master.getSpeciality()==speciality
              && !master.getCalendar().isDateBooked( date )){
               return master;
           }
        }
        throw new IllegalStateException( "There is no masters with given speciality for the chosen Date!" )  ;
    }

    public Master getByNameAndSpeciality(String name, Speciality speciality) {
        for (Master master :masters) {
            if (master.getFullName() == name
                    && master.getSpeciality() == speciality) return master;

        }
        throw new NoSuchElementException( "There is no Master with that name & skills!" );
    }



}

