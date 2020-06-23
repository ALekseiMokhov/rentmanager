package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class MasterRepository implements IMasterRepository {
    private final List <IMaster> masters;

    public MasterRepository() {
        this.masters = new ArrayList <>();
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
            throw new IllegalStateException( "Repository doesn't contain master with such id!" );
        }
        this.masters.remove( this.findById( id ) );
    }

    @Override
    public void save(IMaster master) {
        if (!this.masters.contains( master )) {
            this.masters.add( master );
            System.out.println(master.getCalendar()); /**/
        } else {
            this.masters.set( this.masters.indexOf( master ), master );
        }
    }

    public IMaster getBySpeciality(Speciality speciality) {
        for (IMaster master : masters) {
            if (master.getSpeciality() == speciality) {
                return master;
            }
        }
        throw new IllegalStateException( "There is no masters with given speciality" );
    }

    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        for (IMaster master : masters) {
            if (master.getSpeciality() == speciality
                    && !master.getCalendar().isDateBooked( date )) {
                return master;
            }
        }
        throw new IllegalStateException( "There is no masters with given speciality for the chosen Date!" );
    }

    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        for (IMaster master : masters) {
            if (master.getFullName() == name
                    && master.getSpeciality() == speciality) return master;

        }
        throw new NoSuchElementException( "There is no Master with that name & skills!" );
    }


}

