package com.senla.carservice.view.action.place;

import util.Scanner;

import java.io.IOException;
import java.util.UUID;

public class SetPlaceIdAction extends AbstractPlaceAction{
    @Override
    public void execute() {
        UUID newId = UUID.randomUUID();
        System.out.println(" Enter the old id of place: ");
        try {
            id = UUID.fromString( Scanner.getInstance().getReader().readLine());
        }
        catch (IOException e)  {

        }
        System.out.println(" Enter the id of booked place: ");
        try {
            newId = UUID.fromString( Scanner.getInstance().getReader().readLine());
        }
        catch (IOException e)  {

        }
        place = this.controller.getPlaceById( id );
        this.controller.setPlaceId( place,newId );
        this.controller.savePlace( place );
    }
}
