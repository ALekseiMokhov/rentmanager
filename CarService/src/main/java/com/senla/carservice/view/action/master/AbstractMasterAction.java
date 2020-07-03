package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.view.action.IAction;
import util.ConsoleScanner;

import java.io.BufferedReader;

public abstract class AbstractMasterAction implements IAction {
    MasterController controller = new MasterController();
    BufferedReader reader = ConsoleScanner.getInstance().getReader();
}
