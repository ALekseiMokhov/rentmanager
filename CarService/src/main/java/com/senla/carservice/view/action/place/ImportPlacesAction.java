package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;
import util.csv.CsvPlaceParser;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ImportPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        try {
            List <Place>list = CsvPlaceParser.load();
            for (Place place : list) {
              controller.loadPlace( place);
            }
            System.out.println(list.size() + " places were loaded from file!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
