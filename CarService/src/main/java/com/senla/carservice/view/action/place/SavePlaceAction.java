package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;

import java.io.IOException;
import java.util.UUID;

public class SavePlaceAction extends AbstractPlaceAction {
    private UUID id;
    private Place place;

    @Override
    public void execute() {
        System.out.println( "Enter id of the saving place: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        } catch (IllegalArgumentException e) {
            System.err.println( "UUID should have proper format! " );
        }
        if (this.controller.getPlaceById( id ) != null) {
            place = this.controller.getPlaceById( id );
            this.controller.savePlace( id );
            System.out.println( "Place has been saved with id= " + id );
        } else {
            UUID newId = controller.addPlace();
            System.out.println( "Place has been saved with id= " + newId );
        }


    }
}
