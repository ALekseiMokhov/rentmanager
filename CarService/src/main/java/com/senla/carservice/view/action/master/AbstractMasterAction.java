package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.view.action.IAction;
import util.Scanner;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractMasterAction implements IAction {
    MasterController controller = new MasterController();
    BufferedReader reader = Scanner.getInstance().getReader();
}
