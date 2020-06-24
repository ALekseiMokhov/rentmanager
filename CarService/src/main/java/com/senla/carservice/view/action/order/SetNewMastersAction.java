package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class SetNewMastersAction extends AbstractOrderAction {
    private UUID id;

    @Override
    public void execute() {
        try {
            System.out.println( "Enter the id of the order: " );
            id = UUID.fromString( reader.readLine() );

            controller.setNewMasters( id );
            System.out.println( "New masters serving your order are: " + controller.findOrderById( id ).getMasters() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID shoud have proper format!" );
        }

    }
}
