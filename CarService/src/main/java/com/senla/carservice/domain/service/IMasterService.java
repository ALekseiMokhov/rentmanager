package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import util.calendar.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IMasterService {
    void saveMaster(IMaster master);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id);

    void removeMaster(UUID id);

    IMaster getById(UUID id);

    boolean isBookedForDate(UUID id, LocalDate date);

    void setMasterForDate(UUID id, LocalDate date);

    void setBookedDateFree(UUID id, LocalDate date);

    IMaster getByNameAndSpeciality(String name, Speciality speciality);

    IMaster getBySpeciality(Speciality speciality);

    IMaster getFreeBySpeciality(LocalDate date, Speciality speciality);

    public Set <Speciality> getAvailableSpecialities();

    List <IMaster> getMastersByAlphabet();

    public List <IMaster> getFreeMasters(LocalDate date);

    public List <IMaster> getMastersBySpeciality(Speciality speciality);

    public void loadMastersFromCsv();

    public void exportMastersToCsv();
}
