package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import util.Calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IMasterService {
    void saveMaster(IMaster master);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality);

    void removeMaster(UUID id);

    IMaster getById(UUID id);

    boolean isBookedForDate(IMaster master, LocalDate date);

    void setMasterForDate(IMaster master, LocalDate date);

    void setBookedDateFree(IMaster master, LocalDate date);

    IMaster getByNameAndSpeciality(String name, Speciality speciality);

    IMaster getBySpeciality(Speciality speciality);

    IMaster getFreeBySpeciality(LocalDate date, Speciality speciality);

    public Set <Speciality> getAvailableSpecialities();

    List <IMaster> getMastersByAlphabet();

    public List <IMaster> getFreeMasters(LocalDate date);

    public List <IMaster> getMastersBySpeciality(Speciality speciality);
}
