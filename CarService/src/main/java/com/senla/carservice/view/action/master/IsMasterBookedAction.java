package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class IsMasterBookedAction extends AbstractMasterAction {
    private UUID id;
    private LocalDate date;

    @Override
    public void execute() {
        boolean isBooked = false;
        System.out.println( "Enter the id of master you want to check: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Enter the date to check: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        isBooked = controller.isBookedForDate( id, date );
        System.out.println( "Master with id " + id + " is booked for date " + date + " : " + isBooked );
    }
}
