package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.view.action.IAction;
import lombok.extern.slf4j.Slf4j;
import util.ConsoleScanner;

import java.io.BufferedReader;
@Slf4j
public abstract class AbstractMasterAction implements IAction {

    MasterController controller ;
    BufferedReader reader = ConsoleScanner.getInstance().getReader();
}
