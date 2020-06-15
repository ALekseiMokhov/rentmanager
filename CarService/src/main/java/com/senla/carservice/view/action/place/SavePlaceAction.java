package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;
import util.Calendar;

import java.io.IOException;
import java.util.UUID;

public class SavePlaceAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        System.out.println( " Enter id of the saving place: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        if (this.controller.getPlaceById( id ) != null) {
            place = this.controller.getPlaceById( id );
        } else {
            place = new Place( new Calendar() );
            place.setId( id );
        }
        this.controller.savePlace( place );
        System.out.println( "Place has been saved with id= " + id );
    }
}
