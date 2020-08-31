package view.action.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class BookMasterAction extends AbstracttMasterAction {
    private UUID id;
    private LocalDate date;
    @Autowired
    MasterController controller;
    @Override
    public void execute() {

        try {
            System.out.println( "Enter the id of master : " );
            id = UUID.fromString( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should be in proper format!" );
        }


        try {
            System.out.println( "Enter the date to book: " );
            date = LocalDate.parse( reader.readLine() );
            controller.setMasterForDate( id, date );
            System.out.println( "Master  was booked for " + date.toString() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "Date should be in format 'YYYY-MM-DD'!" );
        }


    }
}
