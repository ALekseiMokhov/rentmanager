package com.senla.carservice.view.action.place;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddPlacesAction extends AbstractPlaceAction {
    private int var = 0;

    @Override
    public void execute() {

        try {
            log.info( "Is controller null : "+(controller==null ));
            System.out.println( "Enter a quantity of new places: " );
            var = Integer.parseInt( reader.readLine() );
            this.controller.addPlaces( var );
            System.out.println( var + " places were added to Garage!" );

        } catch (Exception e) {
            log.error( String.valueOf( e.getStackTrace() ) );
        }

    }
}
