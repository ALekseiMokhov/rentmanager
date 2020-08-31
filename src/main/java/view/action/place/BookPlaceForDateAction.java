package view.action.place;

import com.senla.carservice.controller.PlaceController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class BookPlaceForDateAction extends AbstractPlaceAction {
    private LocalDate date;
    private UUID id;
    @Autowired
    private PlaceController controller;
    @Override
    public void execute() {


        try {
            System.out.println( " Enter the id of booking place: " );
            id = UUID.fromString( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should be in proper format!" );
        }

        try {
            System.out.println( "Enter the Date to book place: " );
            date = LocalDate.parse( reader.readLine() );

            this.controller.setPlaceForDate( id, date );
            System.out.println( "The place was booked successfully!" );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should be in format 'YYYY-MM-DD'" );
        }
    }

}
