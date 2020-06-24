package com.senla.carservice.view.action.place;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class BookPlaceForDateAction extends AbstractPlaceAction {
    private LocalDate date;
    private UUID id;

    @Override
    public void execute() {

        System.out.println( " Enter the id of booking place: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        catch (IllegalArgumentException e){
            System.err.println("UUID should be in proper format!");
        }
        System.out.println( "Enter the Date to book place: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        this.controller.setPlaceForDate( id, date );
        System.out.println( "The place was booked successfully!" );
    }

}
