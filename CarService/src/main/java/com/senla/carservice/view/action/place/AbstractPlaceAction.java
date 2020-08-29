package com.senla.carservice.view.action.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.view.action.IAction;
import lombok.extern.slf4j.Slf4j;
import util.ConsoleScanner;

import java.io.BufferedReader;
@Slf4j
public abstract class AbstractPlaceAction implements IAction  {

    PlaceController controller ;
    BufferedReader reader = ConsoleScanner.getInstance().getReader();
 
 }
