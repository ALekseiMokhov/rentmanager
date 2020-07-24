package com.senla.carservice.view.action.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.view.action.IAction;
import dependency.injection.beanfactory.BeanFactory;
import util.ConsoleScanner;

import java.io.BufferedReader;

public abstract class AbstractPlaceAction implements IAction {
    PlaceController controller = (PlaceController) BeanFactory.getInstance().getSingleton( "placecontroller" );
    BufferedReader reader = ConsoleScanner.getInstance().getReader();

}
