package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.repository.MasterRepository;
import com.senla.carservice.domain.service.MasterService;
import com.senla.carservice.view.action.IAction;
import util.Scanner;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractMasterAction implements IAction {
    MasterController controller = new MasterController( new MasterService( new MasterRepository() ) ) ;
    LocalDate date;
    UUID id;
    IMaster master;
    String fullName;
    double salary;
    Set <Speciality> availableSpecialities = Set.of(Speciality.values());
    Speciality speciality;
    BufferedReader reader = Scanner.getInstance().getReader();
}
