package com.senla.carservice.controller;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.service.IMasterService;
import com.senla.carservice.domain.service.MasterService;
import util.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MasterController {
    private IMasterService masterService;

    public MasterController() {
        this.masterService = MasterService.getInstance();
        ;
    }

    public void saveMasterById(UUID id) {
        IMaster master = this.masterService.getById( id );
        this.masterService.saveMaster( master );
    }

    public void loadMaster(IMaster master) {
        this.masterService.saveMaster( master );
    }

    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.masterService.addMaster( fullName, dailyPayment, calendar, speciality );
    }

    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id) {
        this.masterService.addMaster( fullName, dailyPayment, calendar, speciality, id );
    }

    public void removeMaster(UUID id) {
        this.masterService.removeMaster( id );
    }

    public IMaster getById(UUID id) {
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
    public void loadMastersFromCsv() {
        this.masterService.loadMastersFromCsv();
    }
    public void exportMastersToCsv() {
        this.masterService.exportMastersToCsv();
    }
}
