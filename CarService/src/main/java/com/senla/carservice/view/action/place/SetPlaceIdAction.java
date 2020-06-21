package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;

import java.io.IOException;
import java.util.UUID;

public class SetPlaceIdAction extends AbstractPlaceAction {
    private Place place;
    private UUID id;

    @Override
    public void execute() {
        UUID newId = UUID.randomUUID();
        System.out.println( " Enter the old id of place: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        System.out.println( " Enter the id of booked place: " );
        try {
            newId = UUID.fromString( reader.readLine() );
        } catch (IOException e) {

        }
        place = this.controller.getPlaceById( id );
        this.controller.setPlaceId( id, newId );
        this.controller.savePlace( id );
    }
}
