package view.action.place;

import com.senla.carservice.controller.PlaceController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class IsPlaceSetAction extends AbstractPlaceAction {
    @Autowired
    private PlaceController controller;
    private LocalDate date;
    private UUID id;

    @Override
    public void execute() {
        System.out.println( "Enter UUID of place to check: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "The UUID should have proper format!" );
        }
        System.out.println( "Enter the Date to check: " );

        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "The Date should be formatted like 'YYYY-MM-DD' " );
        }

        boolean isFree = controller.isPlaceSetForDate( id, date );
        System.out.println( " The place with id " + id + " is free: " + isFree );
    }
}
