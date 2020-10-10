package com.senla.carservice.controller;


import com.google.gson.*;
import com.senla.carservice.dto.*;
import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/masters")
/*@Profile({"rest", "test"})*/
public class MasterRestController {
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;
    private Gson gson = new GsonBuilder().create();

    public MasterRestController() {

    }

    /*@PutMapping("save/{json}")
    public void saveMaster(@PathVariable String json) {

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonObject object = element.getAsJsonObject();
        String speciality = object.get("speciality").getAsString();

        switch (speciality) {
            case "ELECTRICIAN" -> {
                ElectricianMasterDto electricianDto = gson.fromJson(json, ElectricianMasterDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.electricianFromDto(electricianDto));
            }

            case "MECHANIC" -> {
                MechanicMasterDto mechanicDto = gson.fromJson(json, MechanicMasterDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.mechanicFromDto(mechanicDto));
            }
            case "RESHAPER" -> {
                ReshaperMasterDto reshaperDto = gson.fromJson(json, ReshaperMasterDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.reshaperFromDto(reshaperDto));
            }
            case "PAINTER" -> {
                PainterMasterDto painterDto = gson.fromJson(json, PainterMasterDto.class);
                this.masterService.saveMaster(MasterMapper.INSTANCE.painterFromDto(painterDto));
            }
        }


    }*/
    @PostMapping("/")
    public void saveMaster(@RequestBody Master master){

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
                this.masterService.saveMaster(MasterMapper.INSTANCE.masterFromDto(dto));


    }


/*    @GetMapping("/{id}")
    public MasterDto getById(@PathVariable UUID id) {
        MasterDto masterDto = convertMasterToDto(this.masterService.getById(id));
        return masterDto;
    }*/

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
        return convertMasterToDto(this.masterService.getByNameAndSpeciality(name, speciality));
    }

   /* @GetMapping("/free/{date}/{speciality}")
    public MasterDto getFreeBySpeciality(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable Speciality speciality) {
        return convertMasterToDto(this.masterService.getFreeBySpeciality(date, speciality));
    }*/

    @GetMapping("/specialities")

    public Set<Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }

  /*  @GetMapping("/")
    public List<MasterDto> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet().stream()
                .map(m -> convertMasterToDto(m))
                .collect(Collectors.toList());
    }*/

/*    @GetMapping("/free")
    public List<MasterDto> getFreeMasters(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.masterService.getFreeMasters(date).stream()
                .map(m -> convertMasterToDto(m))
                .collect(Collectors.toList());
    }*/

 /*   @GetMapping("/specialities/{speciality}")*/
    /*TODO */
/*    public List<MasterDto> getMastersBySpeciality(@PathVariable Speciality speciality) {
        return this.masterService.getMastersBySpeciality(speciality).stream()
                .map(m -> MasterToDto(m))
                .collect(Collectors.toList());*/
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable UUID id) {
        this.masterService.deleteMaster(id);
    }

