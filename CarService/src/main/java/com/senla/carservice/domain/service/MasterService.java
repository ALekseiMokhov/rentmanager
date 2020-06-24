package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.*;
import com.senla.carservice.domain.repository.IMasterRepository;
import com.senla.carservice.domain.repository.MasterRepository;
import util.Calendar;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MasterService implements IMasterService {
    private final IMasterRepository repository;
    private static MasterService instance;

    private MasterService() {
        this.repository = new MasterRepository();
    }

    public static MasterService getInstance() {
        if (instance == null) {
            instance = new MasterService();

        }
        return instance;
    }

    public void saveMaster(IMaster master) {
        this.repository.save( master );

    }

    @Override
    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {

        IMaster master;
        try {
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
                    throw new NoSuchElementException( "there is no suitable speciality!" );
                }

            }
            this.repository.save( master );
        } catch (Exception e) {
            System.err.println("Failed to save master!");
        }



    }

    @Override
    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id) {
        IMaster master;
        switch (speciality) {
            case RESHAPER -> {
                master = new Reshaper( fullName, dailyPayment, calendar, speciality, id );
            }
            case ELECTRICIAN -> {
                master = new Electrician( fullName, dailyPayment, calendar, speciality, id );
            }
            case PAINTER -> {
                master = new Painter( fullName, dailyPayment, calendar, speciality, id );
            }
            case MECHANIC -> {
                master = new Mechanic( fullName, dailyPayment, calendar, speciality, id );
            }
            default -> {
                throw new NoSuchElementException( "There is no suitable speciality!" );
            }
        }
        this.repository.save( master );
    }

    public void removeMaster(UUID id) {
        try {
            this.repository.delete( id );
        }
        catch(NoSuchElementException e){
            System.out.println("The Master with provided id was probably already deleted!");
        }
    }

    @Override
    public IMaster getById(UUID id) {

        return this.repository.findById( id );
    }

    public boolean isBookedForDate(UUID id, LocalDate date) {
        IMaster master = this.repository.findById( id );
        return master.getCalendar().isDateBooked( date );
    }

    public void setMasterForDate(UUID id, LocalDate date) {
        IMaster master = this.repository.findById( id );
        master.getCalendar().setDateForBooking( date );
        this.repository.save( master );
    }

    public void setBookedDateFree(UUID id, LocalDate date) {
        IMaster master = this.repository.findById( id );
        master.getCalendar().deleteBookedDate( date );
    }

    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        try {
            return this.repository.getByNameAndSpeciality( name, speciality );
        } catch (NoSuchElementException e) {
           
        }
        throw new NoSuchElementException( "There is no Master with required a name & skills!" );
    }

    public IMaster getBySpeciality(Speciality speciality) {

        try{
            IMaster  master = this.repository.getBySpeciality( speciality );
            return master;
        }
        catch (IllegalStateException e) {

        }
         throw new NoSuchElementException( " Repository doesn't contain master with provided id!" ) ;
    }

    public Set <Speciality> getAvailableSpecialities() {
        return Set.of( Speciality.values() );
    }

    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        try {
            return this.repository.getFreeBySpeciality( date, speciality );
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException( "There is no masters of required speciality for the chosen Date!" ) ;
    }


    public List <IMaster> getMastersByAlphabet() {
        Comparator <IMaster> comparator = Comparator.comparing( m -> m.getFullName() );
        List <IMaster> sortedList = this.repository.findAll();
        Collections.sort( sortedList, comparator );
        return sortedList;
    }

    public List <IMaster> getFreeMasters(LocalDate date) {
        return this.repository.findAll().stream()
                .filter( (m) -> m.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public List <IMaster> getMastersBySpeciality(Speciality speciality) {
        try {
            return this.repository.findAll().stream()
                    .filter( ((m) -> m.getSpeciality() == speciality) )
                    .collect( Collectors.toList() );
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException( "There is no masters of required speciality" );
    }


}

