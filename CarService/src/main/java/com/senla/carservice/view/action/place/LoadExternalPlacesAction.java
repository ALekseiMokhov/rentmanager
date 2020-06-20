package com.senla.carservice.view.action.place;

import util.csv.CsvPlaceParser;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoadExternalPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        try {
            List <UUID>list = CsvPlaceParser.load();
            for (UUID uuid : list) {
              controller.savePlace( uuid );
            }
            System.out.println(list.size() + " places were loaded from file!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
