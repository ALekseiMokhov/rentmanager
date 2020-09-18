package com.senla.carservice.controller.rest;

import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.util.calendar.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/master_menu")
public class MasterMenuController {
    @Autowired
    private IMasterService masterService;

    @GetMapping("")
    public String showAll(ModelMap model){
        model.addAttribute( "masters", this.masterService.getFreeMasters( LocalDate.now() ));
        return  "master_menu"  ;
    }

    @PostMapping("/addMaster")
    public String addMaster(@RequestParam(required = false) String name,
                            @RequestParam(required = false) Double salary,
                          @RequestParam(required = false) String speciality,
                          ModelMap model) {

        this.masterService.addMaster( name,salary,new Calendar(), Speciality.valueOf( speciality ) );
        model.addAttribute( "message", "Master " + name + " was added! ");

        return "master_menu";
    }

    @PostMapping("/setDate")
    public String setDate(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.masterService.setMasterForDate( id, LocalDate.parse( date ) );
        model.addAttribute( "message", "Master with id " + id + " was set for " + date );
        model.addAttribute( "masters", this.masterService.getMastersByAlphabet() );
        return "master_menu";
    }

}
