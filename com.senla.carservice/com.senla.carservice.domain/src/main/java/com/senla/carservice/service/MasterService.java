package com.senla.carservice.service;


import com.senla.carservice.entity.master.*;
import com.senla.carservice.repository.IMasterRepository;
import com.senla.carservice.util.calendar.Calendar;
import com.senla.carservice.util.csv.CsvMasterParser;
import com.senla.carservice.util.csv.CsvMasterWriter;
import com.senla.carservice.util.serialisation.GsonMasterParser;
import com.senla.carservice.util.serialisation.GsonMasterWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MasterService implements IMasterService {
    @Autowired
    @Qualifier("masterRepositoryJpa")
    private IMasterRepository repository;

    public MasterService() {
    }

    public void saveMaster(IMaster master) {
        try {
            this.repository.save( master );
        } catch (Exception e) {
            log.error( " failed to save master! " + e );
        }

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
            log.error( "Failed to save master!" );
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
        } catch (NoSuchElementException e) {
            log.error( "The Master with provided id was probably already deleted!" );
        }
    }

    @Override
    public IMaster getById(UUID id) {

        try {
            return this.repository.findById( id );
        } catch (Exception e) {
            log.error( "failed to find master by id! " + e );
            throw new RuntimeException();
        }
    }

    public boolean isBookedForDate(UUID id, LocalDate date) {
        IMaster master = null;
        try {
            master = this.repository.findById( id );
        } catch (Exception e) {
            log.error( "failed to find master by id! " + e );
            throw new RuntimeException();
        }

        return master.getCalendar().isDateBooked( date );
    }

    public void setMasterForDate(UUID id, LocalDate date) {
        IMaster master = null;
        try {
            master = this.repository.findById( id );
        } catch (Exception e) {
            log.error( "failed to find master by id! " + e );
            throw new RuntimeException();
        }
        if (master.getCalendar() == null) {
            master.setCalendar( new Calendar() );
        }
        master.getCalendar().setDateForBooking( date );
        try {
            this.repository.save( master );
        } catch (Exception e) {
            log.error( "failed to save master ! " + e );
        }
    }

    public void setBookedDateFree(UUID id, LocalDate date) {
        IMaster master = this.repository.findById( id );
        master.getCalendar().deleteBookedDate( date );
    }

    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        try {
            return this.repository.findAll()
                    .stream()
                    .filter( m -> m.getFullName().equals( name ) )
                    .filter( m -> m.getSpeciality() == speciality )
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            log.error( "There is no required master ! " + e );
            throw new RuntimeException();
        }

    }

    public IMaster getBySpeciality(Speciality speciality) {

        try {
            return this.repository.findAll()
                    .stream()
                    .filter( m -> m.getSpeciality() == speciality )
                    .findFirst()
                    .get();
        } catch (IllegalStateException e) {
            log.error( "failed to find master by speciality! " + e );
            throw new RuntimeException();
        }

    }

    public Set <Speciality> getAvailableSpecialities() {
        return Set.of( Speciality.values() );
    }

    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {

        try {
            return this.repository.findAll()
                    .stream()
                    .filter( m -> m.getSpeciality() == speciality )
                    .filter( m -> m.getCalendar().isDateBooked( date ) == false )
                    .findFirst()
                    .get();
        } catch (Exception e) {
            log.error( "failed to find all masters of chosen specialities for the date" + e );
            throw new RuntimeException();
        }

    }


    public List <IMaster> getMastersByAlphabet() {
        Comparator <IMaster> comparator = Comparator.comparing( m -> m.getFullName() );
        List <IMaster> sortedList = null;
        try {
            sortedList = this.repository.findAll();
        } catch (Exception e) {
            log.error( "failed to find all masters " + e );
            throw new RuntimeException();
        }
        Collections.sort( sortedList, comparator );
        return sortedList;
    }

    public List <IMaster> getFreeMasters(LocalDate date) {
        List <IMaster> res = null;
        try {
            res = this.repository.findAll();
        } catch (Exception e) {
            log.error( "failed to find all masters " + e );
            throw new RuntimeException();
        }
        return res.stream()
                .filter( (m) -> m.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public List <IMaster> getMastersBySpeciality(Speciality speciality) {
        try {
            return this.repository.findAll().stream()
                    .filter( ((m) -> m.getSpeciality() == speciality) )
                    .collect( Collectors.toList() );
        } catch (IllegalStateException e) {
            log.error( "failed to find all masters " + e );
            throw new RuntimeException();
        }

    }

    @Override
    public void loadMastersFromCsv() {
        try {
            List <IMaster> list = CsvMasterParser.load();
            for (IMaster master : list) {
                this.repository.save( master );
            }
        } catch (IOException e) {
            log.error( "failed to load masters to csv! " + e );
            throw new RuntimeException();
        }
    }

    @Override
    public void exportMastersToCsv() {
        try {
            CsvMasterWriter.writeMasters( getMastersByAlphabet() );
            System.out.println( getMastersByAlphabet().size() + " masters were successfully written to csv file!" );
        } catch (IOException e) {
            log.error( "failed to  write masters to csv! " + e );
            throw new RuntimeException();

        }
    }

    @Override
    public void loadMastersFromJson() {
        try {
            List <IMaster> list = GsonMasterParser.load();
            for (IMaster master : list) {
                this.repository.save( master );
            }

        } catch (IOException e) {
            log.error( "failed to load masters from json! " + e );
            throw new RuntimeException();
        }


    }

    @Override
    public void exportMastersToJson() {
        try {
            GsonMasterWriter.serializeMasters( getMastersByAlphabet() );

        } catch (IOException e) {
            log.error( "failed to export  masters to json! " + e );
            throw new RuntimeException();

        }
    }

    @Override
    public void deleteMaster(UUID id) {
        this.repository.delete( id );
    }
}

