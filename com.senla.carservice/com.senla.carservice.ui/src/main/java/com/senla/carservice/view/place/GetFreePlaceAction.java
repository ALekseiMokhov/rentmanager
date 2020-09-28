package com.senla.carservice.view.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.entity.garage.Place;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreePlaceAction extends AbstractPlaceAction {
    private LocalDate date;
    @Autowired
    private PlaceController controller;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter convinient Date: " );
            date = LocalDate.parse( reader.readLine() );

            System.out.println( "Available place is: " );
            Place place = this.controller.getFreePlace( date );
            System.out.println( place );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should be formatted like 'YYYY-MM-DD' " );
        }


    }
}
