package com.senla.carservice.controller;


import com.google.gson.*;
import com.senla.carservice.dto.*;
import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.entity.master.AbstractMaster;
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

    @PutMapping("save/{json}")
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


    }

    @PostMapping("/add/{fullName}/{dailyPayment}/{speciality}")
    public void addMaster(@PathVariable String fullName,
                          @PathVariable double dailyPayment, @PathVariable String speciality) {

        switch (Speciality.valueOf(speciality)) {
            case ELECTRICIAN -> {
                ElectricianMasterDto dto = new ElectricianMasterDto();
                dto.setCalendar(new Calendar());
                dto.setDailyPayment(dailyPayment);
                dto.setFullName(fullName);
                dto.setSpeciality(speciality);
                dto.setId(String.valueOf(UUID.randomUUID()));
                this.masterService.saveMaster(MasterMapper.INSTANCE.electricianFromDto(dto));
            }
            case MECHANIC -> {
                MechanicMasterDto dto = new MechanicMasterDto();
                dto.setCalendar(new Calendar());
                dto.setDailyPayment(dailyPayment);
                dto.setFullName(fullName);
                dto.setSpeciality(speciality);
                dto.setId(String.valueOf(UUID.randomUUID()));
                this.masterService.saveMaster(MasterMapper.INSTANCE.mechanicFromDto(dto));
            }
            case PAINTER -> {
                PainterMasterDto dto = new PainterMasterDto();
                dto.setCalendar(new Calendar());
                dto.setDailyPayment(dailyPayment);
                dto.setFullName(fullName);
                dto.setSpeciality(speciality);
                dto.setId(String.valueOf(UUID.randomUUID()));
                this.masterService.saveMaster(MasterMapper.INSTANCE.painterFromDto(dto));
            }
            case RESHAPER -> {
                ReshaperMasterDto dto = new ReshaperMasterDto();
                dto.setCalendar(new Calendar());
                dto.setDailyPayment(dailyPayment);
                dto.setFullName(fullName);
                dto.setSpeciality(speciality);
                dto.setId(String.valueOf(UUID.randomUUID()));
                this.masterService.saveMaster(MasterMapper.INSTANCE.reshaperFromDto(dto));
            }

        }

    }


    @GetMapping("/{id}")
    public GenericMasterDto getById(@PathVariable UUID id) {
        GenericMasterDto masterDto = convertMasterToDto(this.masterService.getById(id));
        return masterDto;
    }

    @GetMapping("/isbooked/{id}/{date}")
    public boolean isBookedForDate(@PathVariable UUID id,
                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.masterService.isBookedForDate(id, date);
    }

    @PatchMapping("/book/{id}/{date}")
    public void setMasterForDate(@PathVariable UUID id,
                                 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.masterService.setMasterForDate(id, date);
    }

    @PatchMapping("/unbook/{id}/{date}")
    public void setBookedDateFree(@PathVariable UUID id,
                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.masterService.isBookedForDate(id, date);
    }

    @GetMapping("/master/{name}/{speciality}")
    public GenericMasterDto getByNameAndSpeciality(@PathVariable String name,
                                                   @PathVariable Speciality speciality) {
        return convertMasterToDto(this.masterService.getByNameAndSpeciality(name, speciality));
    }

    @GetMapping("/free/{date}/{speciality}")
    public GenericMasterDto getFreeBySpeciality(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable Speciality speciality) {
        return convertMasterToDto(this.masterService.getFreeBySpeciality(date, speciality));
    }

    @GetMapping("/specialities")

    public Set<Speciality> getAvailableSpecialities() {
        return this.masterService.getAvailableSpecialities();
    }

    @GetMapping("/")
    public List<GenericMasterDto> getMastersByAlphabet() {
        return this.masterService.getMastersByAlphabet().stream()
                .map(m -> convertMasterToDto(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/free")
    public List<GenericMasterDto> getFreeMasters(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.masterService.getFreeMasters(date).stream()
                .map(m -> convertMasterToDto(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/specialities/{speciality}")
    public List<GenericMasterDto> getMastersBySpeciality(@PathVariable Speciality speciality) {
        return this.masterService.getMastersBySpeciality(speciality).stream()
                .map(m -> convertMasterToDto(m))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable UUID id) {
        this.masterService.deleteMaster(id);
    }

    private GenericMasterDto convertMasterToDto(AbstractMaster master) {
        switch (master.getSpeciality()) {
            case ELECTRICIAN -> {
                ElectricianMasterDto electricianDto = new ElectricianMasterDto();
                electricianDto.setId(String.valueOf(master.getId()));
                electricianDto.setCalendar
                        (master.getCalendar() != null ? master.getCalendar() : new Calendar());
                electricianDto.setDailyPayment(master.getDailyPayment());
                electricianDto.setFullName(master.getFullName());
                electricianDto.setSpeciality(String.valueOf(master.getSpeciality()));
                return electricianDto;
            }

            case MECHANIC -> {
                MechanicMasterDto mechanicDto = new MechanicMasterDto();
                mechanicDto.setId(String.valueOf(master.getId()));
                mechanicDto.setCalendar
                        (master.getCalendar() != null ? master.getCalendar() : new Calendar());
                mechanicDto.setDailyPayment(master.getDailyPayment());
                mechanicDto.setFullName(master.getFullName());
                mechanicDto.setSpeciality(String.valueOf(master.getSpeciality()));
                return mechanicDto;
            }
            case RESHAPER -> {
                ReshaperMasterDto reshaperDto = new ReshaperMasterDto();
                reshaperDto.setId(String.valueOf(master.getId()));
                reshaperDto.setCalendar
                        (master.getCalendar() != null ? master.getCalendar() : new Calendar());
                reshaperDto.setDailyPayment(master.getDailyPayment());
                reshaperDto.setFullName(master.getFullName());
                reshaperDto.setSpeciality(String.valueOf(master.getSpeciality()));
                return reshaperDto;
            }
            case PAINTER -> {
                PainterMasterDto painterDto = new PainterMasterDto();
                painterDto.setId(String.valueOf(master.getId()));
                painterDto.setCalendar
                        (master.getCalendar() != null ? master.getCalendar() : new Calendar());
                painterDto.setDailyPayment(master.getDailyPayment());
                painterDto.setFullName(master.getFullName());
                painterDto.setSpeciality(String.valueOf(master.getSpeciality()));
                return painterDto;

            }
        }
        throw new IllegalArgumentException("Cant convert master to dto!");
    }


}
