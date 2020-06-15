package com.senla.carservice.view.action.place;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class BookPlaceForDateAction extends AbstractPlaceAction {
    @Override
    public void execute() {

        System.out.println( " Enter the id of booking place: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        System.out.println( "Enter the Date to book place: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        this.controller.setPlaceForDate( this.controller.getPlaceById( id ), date );
        System.out.println( "The place was booked successfully!" );
    }

}
