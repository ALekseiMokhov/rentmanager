package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreePlaceAction extends AbstractPlaceAction {
    private LocalDate date;

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
