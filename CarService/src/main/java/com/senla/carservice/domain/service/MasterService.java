package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.*;
import com.senla.carservice.domain.repository.IMasterRepository;
import util.Calendar;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MasterService implements IMasterService {
    private final IMasterRepository repository;

    public MasterService(IMasterRepository repository) {
        this.repository = repository;
    }

    public void saveMaster(IMaster master) {
        this.repository.save( master );

    }

    @Override
    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        IMaster master;
        switch (speciality) {
            case RESHAPER -> {
                master = new Reshaper( fullName, dailyPayment, calendar, speciality );
            }
            case ELECTRICIAN -> {
                master = new Electrician( fullName, dailyPayment, calendar, speciality );
            }
            case PAINTER -> {
                master = new Painter( fullName, dailyPayment, calendar, speciality );
            }
            case MECHANIC -> {
                master = new Mechanic( fullName, dailyPayment, calendar, speciality );
            }
            default -> {
                throw new IllegalStateException( "there is no suitable speciality!" );
            }
        }
        this.repository.save( master );

    }

    public void removeMaster(UUID id) {
        this.repository.delete( id );
    }

    @Override
    public IMaster getById(UUID id) {

      return   this.repository.findById( id ) ;
    }

    public boolean isBookedForDate(IMaster master, LocalDate date) {
        return master.getCalendar().isDateBooked( date );
    }

    public void setMasterForDate(IMaster master, LocalDate date) {
        master.getCalendar().setDateForBooking( date );
        this.repository.save( master );
    }

    public void setBookedDateFree(IMaster master, LocalDate date) {
        master.getCalendar().deleteBookedDate( date );
    }

    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        return this.repository.getByNameAndSpeciality( name, speciality );
    }

    public IMaster getBySpeciality(Speciality speciality) {
        return this.repository.getBySpeciality( speciality );
    }

    public Set <Speciality> getAvailableSpecialities(){
        return Set.of(Speciality.values());
    }

    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        return this.repository.getFreeBySpeciality( date, speciality );
    }


    public List <IMaster> getMastersByAlphabet() {
        Comparator <IMaster> comparator = Comparator.comparing( m -> m.getFullName() );
        List <IMaster> sortedList = this.repository.findAll();
        Collections.sort( sortedList, comparator );
        return sortedList;
    }

    public List <IMaster> getFreeMasters(LocalDate date) {
        return this.repository
                .findAll()
                .stream()
                .filter( (m) -> m.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public List <IMaster> getMastersBySpeciality(Speciality speciality) {
        return this.repository
                .findAll()
                .stream()
                .filter( ((m) -> m.getSpeciality() == speciality) )
                .collect( Collectors.toList() );
    }


}

