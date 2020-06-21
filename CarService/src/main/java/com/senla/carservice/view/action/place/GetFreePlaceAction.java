package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class GetFreePlaceAction extends AbstractPlaceAction {
    private LocalDate date;

    @Override
    public void execute() {
        System.out.println( "Enter convinient Date: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        System.out.println(
                "Available place is: "
        );
        Place place = this.controller.getFreePlace( date );
        System.out.println( place );

    }
}
