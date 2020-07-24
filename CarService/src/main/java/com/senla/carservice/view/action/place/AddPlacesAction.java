package com.senla.carservice.view.action.place;

import java.io.IOException;

public class AddPlacesAction extends AbstractPlaceAction {
    private int var = 0;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter a quantity of new places: " );
            var = Integer.parseInt( reader.readLine() );

            this.controller.addPlaces( var );
            System.out.println( var + " places were added to Garage!" );

        } catch (Exception e) {

        }

    }
}
