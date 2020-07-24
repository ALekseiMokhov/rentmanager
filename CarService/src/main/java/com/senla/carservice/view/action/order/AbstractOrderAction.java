package com.senla.carservice.view.action.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.view.action.IAction;
import dependency.injection.beanfactory.BeanFactory;
import util.ConsoleScanner;

import java.io.BufferedReader;

public abstract class AbstractOrderAction implements IAction {

    OrderController controller = (OrderController) BeanFactory.getInstance().getSingleton( "ordercontroller" );

    BufferedReader reader = ConsoleScanner.getInstance().getReader();


}
