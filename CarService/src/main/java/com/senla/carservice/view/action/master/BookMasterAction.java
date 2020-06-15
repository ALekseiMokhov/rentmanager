package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class BookMasterAction extends AbstractMasterAction {
    @Override
    public void execute() {
        System.out.println( "Enter the id of master : " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        master = controller.getById( id );

        System.out.println( "Enter the date to book: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        controller.setMasterForDate( master, date );

        System.out.println( "Master " + master.getFullName() + " was booked for " + date.toString() );
    }
}
