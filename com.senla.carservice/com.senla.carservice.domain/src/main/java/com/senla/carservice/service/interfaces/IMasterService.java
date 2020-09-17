package com.senla.carservice.service.interfaces;


import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface IMasterService {
    void saveMaster(AbstractMaster master);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id);

    void removeMaster(UUID id);

    AbstractMaster getById(UUID id);

    boolean isBookedForDate(UUID id, LocalDate date);

    void setMasterForDate(UUID id, LocalDate date);

    void setBookedDateFree(UUID id, LocalDate date);

    AbstractMaster getByNameAndSpeciality(String name, Speciality speciality);

    AbstractMaster getBySpeciality(Speciality speciality);

    AbstractMaster getFreeBySpeciality(LocalDate date, Speciality speciality);

    Set <Speciality> getAvailableSpecialities();

    List <AbstractMaster> getMastersByAlphabet();

    List <AbstractMaster> getFreeMasters(LocalDate date);

    List <AbstractMaster> getMastersBySpeciality(Speciality speciality);

    void loadMastersFromCsv();

    void exportMastersToCsv();

    void loadMastersFromJson();

    void exportMastersToJson();

    void deleteMaster(UUID id);
}
