package com.senla.carservice.view.place;

import com.senla.carservice.controller.PlaceController;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportPlacesAction extends AbstractPlaceAction {
    @Autowired
    private PlaceController controller;

    @Override
    public void execute() {
        controller.exportPlacesToCsv();
    }
}
