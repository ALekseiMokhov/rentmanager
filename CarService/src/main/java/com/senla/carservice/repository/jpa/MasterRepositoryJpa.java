package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.repository.IMasterRepository;
import dependency.injection.annotations.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Qualifier
public class MasterRepositoryJpa implements IMasterRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(MasterRepositoryJpa.class  )   ;
    @Override
    public IMaster findById(UUID id) {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public List <IMaster> findAll() {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        try{

        }
        catch(Exception e){

        }

    }

    @Override
    public void save(IMaster master) {
        try{

        }
        catch(Exception e){

        }

    }

    @Override
    public IMaster getBySpeciality(Speciality speciality) {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        return null;
    }
}
