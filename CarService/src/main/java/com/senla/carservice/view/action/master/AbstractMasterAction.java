package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.view.action.IAction;
import dependency.injection.beanfactory.BeanFactory;
import util.ConsoleScanner;

import java.io.BufferedReader;

public abstract class AbstractMasterAction implements IAction {
    MasterController controller = (MasterController) BeanFactory.getInstance().getSingleton( "mastercontroller" );
    BufferedReader reader = ConsoleScanner.getInstance().getReader();
}
