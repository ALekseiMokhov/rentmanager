package com.senla.carservice.view.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.entity.garage.Place;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class SetPlaceIdAction extends AbstractPlaceAction {
    private Place place;
    private UUID id;
    @Autowired
    private PlaceController controller;

    @Override
    public void execute() {
        UUID newId = UUID.randomUUID();
        try {
            System.out.println( " Enter the old id of place: " );
            id = UUID.fromString( reader.readLine() );
            place = this.controller.getPlaceById( id );
            System.out.println( " Enter the new id of the booked place: " );
            newId = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "UUID should be in proper format!" );
        }

        this.controller.setPlaceId( id, newId );

    }
}
