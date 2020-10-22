package com.senla.carservice.controller.template;

import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/masters")
@Profile("ui")
public class MasterMenuController {
    @Autowired
    private IMasterService masterService;

    @GetMapping("")
    public String showAll(ModelMap model) {
        model.addAttribute("masters", this.masterService.getMastersByAlphabet());
        return "masters";
    }

    @GetMapping("/name-spec/")
    public String getByNameAndSpeciality(@RequestParam String name,
                                         @RequestParam String speciality,
                                         ModelMap model) {
        model.addAttribute("message",
                "Required master is: \t" +
                        this.masterService.getByNameAndSpeciality(name, Speciality.valueOf(speciality)));

        return "masters";
    }

    @GetMapping("/free")
    public String getFreeMaster(@RequestParam String date,
                                @RequestParam String speciality,
                                ModelMap model) {
        model.addAttribute("message", "Free master is: " + this.masterService.getFreeBySpeciality(LocalDate.parse(date), Speciality.valueOf(speciality)));
        return "masters";
    }

    @GetMapping("/free-all")
    public String getFreeMasters(@RequestParam(required = false) String date,
                                 ModelMap model) {
        model.addAttribute("message", "Free masters are: " + this.masterService.getFreeMasters(LocalDate.parse(date)));
        return "masters";
    }

    @GetMapping("/speciality")
    public String getMastersBySpeciality(@RequestParam(required = false) String speciality,
                                         ModelMap model) {
        model.addAttribute("message", "" + speciality + "'s available: " + this.masterService.getBySpeciality(Speciality.valueOf(speciality)));
        return "masters";
    }

    @PostMapping("/add")
    public String addMaster(@RequestParam(required = false) String name,
                            @RequestParam(required = false) Double salary,
                            @RequestParam(required = false, defaultValue = "MECHANIC") String speciality,
                            ModelMap model) {

        this.masterService.addMaster(name, salary, new Calendar(), Speciality.valueOf(speciality));
        model.addAttribute("message", "Master " + name + " was added! ");
        model.addAttribute("masters", this.masterService.getMastersByAlphabet());

        return "masters";
    }

    @PostMapping("/set-date")
    public String setDate(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.masterService.setMasterForDate(id, LocalDate.parse(date));
        model.addAttribute("message", "Master with id " + id + " was set for " + date);
        model.addAttribute("masters", this.masterService.getMastersByAlphabet());
        return "mastersu";
    }

    @PostMapping("/delete")
    public String deleteMaster(@RequestParam(required = false) UUID id,

                               ModelMap model) {

        this.masterService.deleteMaster(id);
        model.addAttribute("message", "Master with id " + id + " was successfully deleted");
        model.addAttribute("masters", this.masterService.getMastersByAlphabet());
        return "masters";
    }

    @PostMapping("/un-book")
    public String unbookMaster(@RequestParam(required = false) UUID id,
                               @RequestParam(required = false) String date,
                               ModelMap model) {

        this.masterService.setBookedDateFree(id, LocalDate.parse(date));
        model.addAttribute("message", "Master with id " + id + " was set free for " + date);
        model.addAttribute("masters", this.masterService.getMastersByAlphabet());
        return "masters";
    }

}
