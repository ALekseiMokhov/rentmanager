package com.senla.carservice.controller;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/masters")
/*@Profile({"rest", "test"})*/
public class MasterRestController {
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;

    public MasterRestController() {

    }


    @PostMapping("/")
    public void saveMaster(@RequestBody Master master) {

    }

    @PostMapping("/{fullName}/{dailyPayment}/{speciality}")
    public void addMaster(@PathVariable String fullName,
                          @PathVariable double dailyPayment, @PathVariable String speciality) {
        MasterDto dto = new MasterDto();
        dto.setCalendar(new Calendar());
        dto.setDailyPayment(dailyPayment);
        dto.setFullName(fullName);
        dto.setSpeciality(speciality);
        dto.setId(String.valueOf(UUID.randomUUID()));
        this.masterService.saveMaster(dto);


    }


    @GetMapping("/{id}")
    public MasterDto getById(@PathVariable UUID id) {

        return this.masterService.getById(id);
    }

    @GetMapping("/is-booked/{id}/{date}")
    public boolean isBookedForDate(@PathVariable UUID id,
                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.masterService.isBookedForDate(id, date);
    }

    @PatchMapping("/book/{id}/{date}")
    public void setMasterForDate(@PathVariable UUID id,
                                 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.masterService.setMasterForDate(id, date);
    }

    @PatchMapping("/un-book/{id}/{date}")
    public void setBookedDateFree(@PathVariable UUID id,
                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.masterService.isBookedForDate(id, date);
    }

    @GetMapping("/master/{name}/{speciality}")
    public MasterDto getByNameAndSpeciality(@PathVariable String name,
                                            @PathVariable Speciality speciality) {
        return this.masterService.getByNameAndSpeciality(name, speciality);
    }

    @GetMapping("/free/{date}/{speciality}")
    public MasterDto getFreeBySpeciality(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable Speciality speciality) {
        return this.masterService.getFreeBySpeciality(date, speciality);
    }

    @GetMapping("/specialities")

    public Set<Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }

    @GetMapping("/")
    public List<MasterDto> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet();
    }

    @GetMapping("/free")
    public List<MasterDto> getFreeMasters(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.masterService.getFreeMasters(date);
    }

    @GetMapping("/type")
    public List<MasterDto> getMastersBySpeciality(@RequestParam String speciality) {
        return this.masterService.getMastersBySpeciality(Speciality.valueOf(speciality));
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable UUID id) {
        this.masterService.deleteMaster(id);
    }

}