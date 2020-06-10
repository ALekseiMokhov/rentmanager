package main.service;

import main.entities.master.Master;
import main.entities.master.Speciality;
import main.repository.MasterRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MasterService {
    private final MasterRepository repository;

    public MasterService(MasterRepository repository) {
        this.repository = repository;
    }

    public void saveMaster(Master master) {
        this.repository.save( master );

    }

    public void removeMaster(UUID id) {
        this.repository.delete( id );
    }


    public boolean isBookedForDate(Master master, LocalDate date) {
        return master.getCalendar().isDateBooked( date );
    }

    public void setMasterForDate(Master master, LocalDate date) {
        master.getCalendar().setDateForBooking( date );
        this.repository.save( master );
    }

    public void setBookedDateFree(Master master, LocalDate date) {
        master.getCalendar().deleteBookedDate( date );
    }

    public Master getByNameAndSpeciality(String name, Speciality speciality) {
        return this.repository.getByNameAndSpeciality( name, speciality );
    }

    public Master getBySpeciality(Speciality speciality) {
        return this.repository.getBySpeciality( speciality );
    }


    public Master getFreeBySpeciality(LocalDate date, Speciality speciality) {
        return this.repository.getFreeBySpeciality( date, speciality );
    }


    public List <Master> getMastersByAlphabet() {
        Comparator <Master> comparator = Comparator.comparing( m -> m.getFullName() );
        List <Master> sortedList = this.repository.findAll();
        Collections.sort( sortedList, comparator );
        return sortedList;
    }

    public List <Master> getFreeMasters(LocalDate date) {
        return this.repository
                .findAll()
                .stream()
                .filter( (m) -> m.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public List <Master> getMastersBySpeciality(Speciality speciality) {
        return this.repository
                .findAll()
                .stream()
                .filter( ((m) -> m.getSpeciality() == speciality) )
                .collect( Collectors.toList() );
    }


}

