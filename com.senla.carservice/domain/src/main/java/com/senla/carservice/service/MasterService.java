package com.senla.carservice.service;


import com.senla.carservice.entity.master.*;
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

    public MasterService() {
    }

    public void saveMaster(Master master) {
        if (this.repository.getById(master.getId()) != null) {
            this.repository.update(master);
        } else {this.repository.save(master);
        }

    }


    @Override
    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {

        Master  master = new Master(fullName, dailyPayment, calendar, speciality);

        this.repository.save(master);

    }


    public void removeMaster(UUID id) {
        this.repository.delete(id);

    }

    @Override
    public Master getById(UUID id) {
        return this.repository.getById(id);
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

    public Master getByNameAndSpeciality(String name, Speciality speciality) {

        return this.repository.findAll()
                .stream()
                .filter(m -> (m).getFullName().equals(name))
                .filter(m -> (m).getSpeciality() == speciality)
                .findFirst()
                .get();


    }

    public Master getBySpeciality(Speciality speciality) {

        return this.repository.findAll()
                .stream()
                .filter(m -> (m).getSpeciality() == speciality)
                .findFirst()
                .get();

    }

    public Set<Speciality> getAvailableSpecialities() {
        return Set.of(Speciality.values());
    }

    public Master getFreeBySpeciality(LocalDate date, Speciality speciality) {

        return this.repository.findAll()
                .stream()
                .filter(m -> (m).getSpeciality() == speciality)
                .filter(m -> (m).getCalendar().isDateBooked(date) == false)
                .findFirst()
                .get();


    }


    public List<Master> getMastersByAlphabet() {
        Comparator<Master> comparator = Comparator.comparing(m -> m.getFullName());
        List<Master>
                sortedList = this.repository.findAll();
        Collections.sort(sortedList, comparator);
        return sortedList;
    }

    public List<Master> getFreeMasters(LocalDate date) {
        List<Master> res = this.repository.findAll();
        return res.stream()
                .filter((m) -> m.getCalendar().isDateBooked(date) == false)
                .collect(Collectors.toList());
    }

    public List<Master> getMastersBySpeciality(Speciality speciality) {

        return this.repository.findAll().stream()
                .filter(((m) -> (m).getSpeciality() == speciality))
                .collect(Collectors.toList());


    }


    @Override
    public void deleteMaster(UUID id) {
        this.repository.delete(id);
    }


}

