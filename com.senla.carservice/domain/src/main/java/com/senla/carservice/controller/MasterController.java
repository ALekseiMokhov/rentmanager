package com.senla.carservice.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.carservice.dto.ElectricianDto;
import com.senla.carservice.dto.MechanicDto;
import com.senla.carservice.dto.PainterDto;
import com.senla.carservice.dto.ReshaperDto;
import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/masters")
/*@Profile({"rest", "test"})*/
public class MasterController {
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;

    public MasterController() {

    }
    @PutMapping("save/{json}")
    public void saveMaster(@PathVariable String json) {
        Gson gson = new GsonBuilder().create();
        ElectricianDto electricianDto;
        ReshaperDto reshaperDto;
        MechanicDto mechanicDto;
        PainterDto painterDto;
        switch (parseSpecialityFromJson(json)){
            case ELECTRICIAN ->  { electricianDto= gson.fromJson(json, ElectricianDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.electricianFromDto(electricianDto));}

            case MECHANIC -> {mechanicDto = gson.fromJson(json,MechanicDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.mechanicFromDto(mechanicDto));
            }
            case RESHAPER -> {reshaperDto = gson.fromJson(json,ReshaperDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.reshaperFromDto(reshaperDto));
            }
            case PAINTER -> {painterDto = gson.fromJson(json,PainterDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.painterFromDto(painterDto)); }
        }

    }


    public void addMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.masterService.addMaster(fullName, dailyPayment, calendar, speciality);
    }
    @DeleteMapping("/delete/{id}")
    public void removeMaster(@PathVariable UUID id) {
        this.masterService.removeMaster(id);
    }
    @GetMapping("/{id}")
    public AbstractMaster getById(@PathVariable UUID id) {
        return this.masterService.getById(id);
    }
    @GetMapping("/isBooked/{id}/{date}")
    public boolean isBookedForDate(@PathVariable UUID id,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDate date) {
        return this.masterService.isBookedForDate(id, date);
    }
    @PatchMapping("/book/{id}/{date}")
    public void setMasterForDate(@PathVariable UUID id,@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {
        this.masterService.setMasterForDate(id, date);
    }
    @PatchMapping("/unbook/{id}/{date}")
    public void setBookedDateFree(@PathVariable UUID id,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {
        this.masterService.isBookedForDate(id, date);
    }
    @GetMapping("/master/{name}/{speciality}")
    public AbstractMaster getByNameAndSpeciality(@PathVariable String name,@PathVariable Speciality speciality) {
        return this.masterService.getByNameAndSpeciality(name, speciality);
    }
    @GetMapping("/free/{name}/{speciality}")
    public AbstractMaster getFreeBySpeciality(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDate date,@PathVariable Speciality speciality) {
        return this.masterService.getFreeBySpeciality(date, speciality);
    }
    @GetMapping("/specialities")

    public Set<Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }
    @GetMapping("/")
    public List<AbstractMaster> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet();
    }
    @GetMapping("/free")
    public List<AbstractMaster> getFreeMasters(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate date) {
        return this.masterService.getFreeMasters(date);
    }
    @GetMapping("/specialities/{speciality}")
    public List<AbstractMaster> getMastersBySpeciality(@PathVariable Speciality speciality) {
        return this.masterService.getMastersBySpeciality(speciality);
    }
    @DeleteMapping("{id}")
    public void deleteMaster(UUID id) {
        this.masterService.deleteMaster(id);
    }

    private Speciality parseSpecialityFromJson(String json){
        Gson gson = new GsonBuilder().create();
        List<Map<String,String>> listFromJson = gson.fromJson(json, ArrayList.class);
        Map<String,String> intermediateMap = listFromJson.stream()
                .filter(m->m.containsKey("speciality"))
                .findFirst()
                .get();
        Speciality speciality = Speciality.valueOf(intermediateMap.get("speciality"));
        return speciality;
    }
}
