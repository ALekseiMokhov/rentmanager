package com.senla.carservice.view.action.place;

import util.ConsoleScanner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class UnbookPlaceAction extends AbstractPlaceAction {
    private LocalDate date;
    private UUID id;

    @Override
    public void execute() {

        try {
            System.out.println( " Enter the id of booked place: " );
            id = UUID.fromString( ConsoleScanner.getInstance().getReader().readLine() );
            System.out.println( "Enter the Date to unbook place: " );
            date = LocalDate.parse( ConsoleScanner.getInstance().getReader().readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "Date and UUID should have proper format!" );
        }


        this.controller.setPlaceFree( id, date );
        System.out.println( "The place was unbooked successfully!" );
    }
}
