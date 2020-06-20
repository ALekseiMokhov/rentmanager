package com.senla.carservice.view.action.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.view.action.IAction;
import util.ConsoleScanner;

import java.io.BufferedReader;

public abstract class AbstractPlaceAction implements IAction {
    PlaceController controller = new PlaceController();
    BufferedReader reader = ConsoleScanner.getInstance().getReader();

}
