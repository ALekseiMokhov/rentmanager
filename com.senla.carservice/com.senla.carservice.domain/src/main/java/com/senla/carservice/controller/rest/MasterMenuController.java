package com.senla.carservice.controller.rest;

import com.senla.carservice.service.interfaces.IMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/master_menu")
public class MasterMenuController {
    @Autowired
    private IMasterService masterService;

    @GetMapping()
    public String showAll(ModelMap model){
        model.addAttribute( "masters", this.masterService.getMastersByAlphabet() );
        return  "master_menu"  ;
    }
}
