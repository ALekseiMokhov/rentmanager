package com.senla.carservice.service;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MasterService implements IMasterService {
    @Autowired
    @Qualifier("masterJpaRepository")
    private IGenericRepository<Master> repository;
    @Autowired
    private MasterMapper mapper;

    public void saveMaster(MasterDto masterDto) {
        Master master = this.mapper.masterFromDto(masterDto);
        if (this.repository.getById(master.getId()) != null) {
            this.repository.update(master);
        } else {
            this.repository.save(master);
        }

    }


    @Override
    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {

        Master master = new Master(fullName, dailyPayment, calendar, speciality);

        this.repository.save(master);

    }


    public void removeMaster(UUID id) {
        this.repository.delete(id);

    }

    @Override
    public MasterDto getById(UUID id) {
        return this.mapper.masterToDto(this.repository.getById(id)) ;
    }


    public boolean isBookedForDate(UUID id, LocalDate date) {
        Master master = this.repository.getById(id);


        return master.getCalendar().isDateBooked(date);
    }


    public void setMasterForDate(UUID id, LocalDate date) {
        Master master = this.repository.getById(id);
        if (master.getCalendar() == null) {
            master.setCalendar(new Calendar());
        }
        master.getCalendar().setDateForBooking(date);

    }

    public void setBookedDateFree(UUID id, LocalDate date) {
        Master master = this.repository.getById(id);
        master.getCalendar().deleteBookedDate(date);
    }

    public MasterDto getByNameAndSpeciality(String name, Speciality speciality) {

        Master master = this.repository.findAll()
                .stream()
                .filter(m -> (m).getFullName().equals(name))
                .filter(m -> (m).getSpeciality() == speciality)
                .findFirst()
                .get();
        return this.mapper.masterToDto(master);

    }

    public MasterDto getBySpeciality(Speciality speciality) {

        Master master = this.repository.findAll()
                .stream()
                .filter(m -> (m).getSpeciality() == speciality)
                .findFirst()
                .get();
        return this.mapper.masterToDto(master);
    }

    public Set<Speciality> getAvailableSpecialities() {
        return Set.of(Speciality.values());
    }

    public MasterDto getFreeBySpeciality(LocalDate date, Speciality speciality) {

        Master master = this.repository.findAll()
                .stream()
                .filter(m -> (m).getSpeciality() == speciality)
                .filter(m -> (m).getCalendar().isDateBooked(date) == false)
                .findFirst()
                .get();
        return this.mapper.masterToDto(master);

    }


    public List<MasterDto> getMastersByAlphabet() {
        Comparator<Master> comparator = Comparator.comparing(m -> m.getFullName());
        List<Master>
                sortedList = this.repository.findAll();
        Collections.sort(sortedList, comparator);
        return this.mapper.mastersToDto(sortedList);
    }

    public List<MasterDto> getFreeMasters(LocalDate date) {
        List<Master> res = this.repository.findAll().stream()
                .filter((m) -> m.getCalendar().isDateBooked(date) == false)
                .collect(Collectors.toList());
        return this.mapper.mastersToDto(res);
    }

    public List<MasterDto> getMastersBySpeciality(Speciality speciality) {

        List<Master> res = this.repository.findAll().stream()
                .filter(((m) -> (m).getSpeciality() == speciality))
                .collect(Collectors.toList());
        return this.mapper.mastersToDto(res);
    }


    @Override
    public void deleteMaster(UUID id) {
        this.repository.delete(id);
    }


}

