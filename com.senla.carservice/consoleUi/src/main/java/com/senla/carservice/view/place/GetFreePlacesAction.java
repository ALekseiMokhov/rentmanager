package com.senla.carservice.view.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.entity.garage.Place;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GetFreePlacesAction extends AbstractPlaceAction {
    private LocalDate date;
    @Autowired
    private PlaceController controller;

    @Override
    public void execute() {

        System.out.println( "Enter convenient Date: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "The Date should be formatted like 'YYYY-MM-DD' " );
        }
        System.out.println(
                "Available places are: "
        );
        List <Place> placeList = this.controller.getFreePlacesForDate( date );
        for (Place val : placeList) {
            System.out.println( val );
        }
    }
}
