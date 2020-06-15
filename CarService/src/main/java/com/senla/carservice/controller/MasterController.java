package com.senla.carservice.controller;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.service.IMasterService;
import util.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MasterController {
    private IMasterService masterService;

    public MasterController(IMasterService masterService) {
        this.masterService = masterService;
    }

    public void saveMaster(IMaster master) {
        this.masterService.saveMaster( master );
    }

    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.masterService.addMaster( fullName, dailyPayment, calendar, speciality );
    }

    public void removeMaster(UUID id) {
        this.masterService.removeMaster( id );
    }
    public IMaster getById(UUID id){
        return this.masterService.getById( id ) ;
    }

    public boolean isBookedForDate(IMaster master, LocalDate date) {
        return this.masterService.isBookedForDate( master, date );
    }

    public void setMasterForDate(IMaster master, LocalDate date) {
        this.masterService.setMasterForDate( master, date );
    }

    public void setBookedDateFree(IMaster master, LocalDate date) {
        this.masterService.isBookedForDate( master, date );
    }

    public IMaster getByNameAndSpeciality(String name, Speciality speciality) {
        return this.masterService.getByNameAndSpeciality( name, speciality );
    }

    public IMaster getBySpeciality(Speciality speciality) {
        return this.masterService.getBySpeciality( speciality );
    }

    public IMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        return this.masterService.getFreeBySpeciality( date, speciality );
    }

    public Set <Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }

    public List <IMaster> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet();
    }

    public List <IMaster> getFreeMasters(LocalDate date) {
        return this.masterService.getFreeMasters( date );
    }

    public List <IMaster> getMastersBySpeciality(Speciality speciality) {
        return this.masterService.getMastersBySpeciality( speciality );
    }
}
