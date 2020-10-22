package com.senla.carservice.service.interfaces;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service

public interface IMasterService {
    void saveMaster(MasterDto masterDto);

    void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality);

    void removeMaster(UUID id);

    MasterDto getById(UUID id);

    boolean isBookedForDate(UUID id, LocalDate date);

    void setMasterForDate(UUID id, LocalDate date);

    void setBookedDateFree(UUID id, LocalDate date);

    MasterDto getByNameAndSpeciality(String name, Speciality speciality);

    MasterDto getBySpeciality(Speciality speciality);

    MasterDto getFreeBySpeciality(LocalDate date, Speciality speciality);

    Set<Speciality> getAvailableSpecialities();

    List<MasterDto> getMastersByAlphabet();

    List<MasterDto> getFreeMasters(LocalDate date);

    List<MasterDto> getMastersBySpeciality(Speciality speciality);

    void deleteMaster(UUID id);
}
