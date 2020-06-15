package com.senla.carservice.view.action.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.repository.PlaceRepository;
import com.senla.carservice.domain.service.PlaceService;
import com.senla.carservice.view.action.IAction;
import util.Scanner;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.UUID;

public abstract class AbstractPlaceAction implements IAction {
    PlaceController controller = new PlaceController(new PlaceService( new PlaceRepository() ) ) ;
    BufferedReader reader = Scanner.getInstance().getReader();
    LocalDate date;
    UUID id;
    Place place;
}
