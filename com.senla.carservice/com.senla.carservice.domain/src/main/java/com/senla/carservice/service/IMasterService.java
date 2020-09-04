package com.senla.carservice.service;


import com.senla.carservice.entity.master.IMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

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

    Set <Speciality> getAvailableSpecialities();

    List <IMaster> getMastersByAlphabet();

    List <IMaster> getFreeMasters(LocalDate date);

    List <IMaster> getMastersBySpeciality(Speciality speciality);

    void loadMastersFromCsv();

    void exportMastersToCsv();

    void loadMastersFromJson();

    void exportMastersToJson();

    void deleteMaster(UUID id) ;
}
