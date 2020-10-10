package com.senla.carservice.service.interfaces;


import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface IMasterService {
    void saveMaster(Master master);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality);

    void removeMaster(UUID id);

    Master getById(UUID id);

    boolean isBookedForDate(UUID id, LocalDate date);

    void setMasterForDate(UUID id, LocalDate date);

    void setBookedDateFree(UUID id, LocalDate date);

    Master getByNameAndSpeciality(String name, Speciality speciality);

    Master getBySpeciality(Speciality speciality);

    Master getFreeBySpeciality(LocalDate date, Speciality speciality);

    Set<Speciality> getAvailableSpecialities();

    List<Master> getMastersByAlphabet();

    List<Master> getFreeMasters(LocalDate date);

    List<Master> getMastersBySpeciality(Speciality speciality);

    void deleteMaster(UUID id);
}
