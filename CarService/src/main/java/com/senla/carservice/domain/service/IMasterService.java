package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IMasterService {
    void saveMaster(IMaster master);

    void removeMaster(UUID id);

    boolean isBookedForDate(IMaster master, LocalDate date);

    void setMasterForDate(IMaster master, LocalDate date);

    void setBookedDateFree(IMaster master, LocalDate date);

    IMaster getByNameAndSpeciality(String name, Speciality speciality);

    IMaster getBySpeciality(Speciality speciality);

    IMaster getFreeBySpeciality(LocalDate date, Speciality speciality);

    List <IMaster> getMastersByAlphabet();

    public List <IMaster> getFreeMasters(LocalDate date);

    public List <IMaster> getMastersBySpeciality(Speciality speciality);
}
