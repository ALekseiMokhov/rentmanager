package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class UnbookMasterAction extends AbstractMasterAction {
    private LocalDate date;
    private IMaster master;
    private UUID id;

    @Override
    public void execute() {
        try {
            System.out.println( "Enter the id of the master : " );
            id = UUID.fromString( reader.readLine() );

            master = controller.getById( id );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

        try {
            System.out.println( "Enter the date to unbook: " );
            date = LocalDate.parse( reader.readLine() );

            controller.setBookedDateFree( id, date );
            System.out.println( "Master " + master.getFullName() + " is free for date " + date.toString() );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have format 'YYYY-MM-DD'" );
        }

    }
}
