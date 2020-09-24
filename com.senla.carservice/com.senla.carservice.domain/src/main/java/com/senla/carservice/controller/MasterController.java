package com.senla.carservice.controller;


import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class MasterController {
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;

    public MasterController() {

    }

    public void saveMaster(AbstractMaster master) {
        this.masterService.saveMaster( master );
    }

    public void loadMaster(AbstractMaster master) {
        this.masterService.saveMaster( master );
    }

    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.masterService.addMaster( fullName, dailyPayment, calendar, speciality );
    }

    public void removeMaster(UUID id) {
        this.masterService.removeMaster( id );
    }

    public AbstractMaster getById(UUID id) {
        return this.masterService.getById( id );
    }

    public boolean isBookedForDate(UUID id, LocalDate date) {
        return this.masterService.isBookedForDate( id, date );
    }

    public void setMasterForDate(UUID id, LocalDate date) {
        this.masterService.setMasterForDate( id, date );
    }

    public void setBookedDateFree(UUID id, LocalDate date) {
        this.masterService.isBookedForDate( id, date );
    }

    public AbstractMaster getByNameAndSpeciality(String name, Speciality speciality) {
        return this.masterService.getByNameAndSpeciality( name, speciality );
    }

    public AbstractMaster getBySpeciality(Speciality speciality) {
        return this.masterService.getBySpeciality( speciality );
    }

    public AbstractMaster getFreeBySpeciality(LocalDate date, Speciality speciality) {
        return this.masterService.getFreeBySpeciality( date, speciality );
    }

    public Set <Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }

    public List <AbstractMaster> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet();
    }

    public List <AbstractMaster> getFreeMasters(LocalDate date) {
        return this.masterService.getFreeMasters( date );
    }

    public List <AbstractMaster> getMastersBySpeciality(Speciality speciality) {
        return this.masterService.getMastersBySpeciality( speciality );
    }

    public void deleteMaster(UUID id) {
        this.masterService.deleteMaster( id );
    }
}
