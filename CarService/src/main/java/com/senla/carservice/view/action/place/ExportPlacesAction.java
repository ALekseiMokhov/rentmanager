package com.senla.carservice.view.action.place;

import util.csv.CsvPlaceWriter;

import java.io.IOException;

public class ExportPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        try {
            CsvPlaceWriter.writePlaces( controller.getPlaces() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( controller.getPlaces().size() + " places were successfully written to csv file!" );
    }
}
