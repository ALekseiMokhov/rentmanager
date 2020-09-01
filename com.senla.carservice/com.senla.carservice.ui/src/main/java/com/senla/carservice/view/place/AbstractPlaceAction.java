package com.senla.carservice.view.place;



import com.senla.carservice.view.action.IAction;

import java.io.BufferedReader;


public abstract class AbstractPlaceAction implements IAction {

    protected BufferedReader reader = com.senla.carservice.scanner.ConsoleScanner.getInstance().getReader();


}
