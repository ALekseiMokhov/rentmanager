package com.senla.carservice.view.action.place;

import java.io.IOException;

public class AddPlacesAction extends AbstractPlaceAction {
    private int var = 0;

    @Override
    public void execute() {

        System.out.println( "Enter a quantity of new places: " );
        try {
            var = Integer.parseInt( reader.readLine() );
        } catch (IOException e) {

        }
        this.controller.addPlaces( var );
        System.out.println( var + " places were added to Garage!" );
    }
}
