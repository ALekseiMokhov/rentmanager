package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;
import util.Scanner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class IsPlaceSetAction extends AbstractPlaceAction {
    private LocalDate date;
    private UUID id;

    @Override
    public void execute() {
        System.out.println( "Enter UUID of place to check: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        System.out.println( "Enter the Date to check: " );

        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }

        boolean isFree = controller.isPlaceSetForDate( id, date );
        System.out.println( " The place with id " + id + " is free: " + isFree );
    }
}
