package view.action.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class IsMasterBookedAction extends AbstracttMasterAction {
    private UUID id;
    private LocalDate date;
    private boolean isBooked;
    @Autowired
    MasterController controller;
    @Override
    public void execute() {

        try {
            System.out.println( "Enter the id of master you want to check: " );
            id = UUID.fromString( reader.readLine() );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

        try {
            System.out.println( "Enter the date to check: " );
            date = LocalDate.parse( reader.readLine() );
            isBooked = controller.isBookedForDate( id, date );
            System.out.println( "Master with id " + id + " is booked for date " + date + " : " + isBooked );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have format 'YYYY-MM-DD'" );
        }

    }
}
