package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class CompleteOrderAction extends AbstractOrderAction {
    private UUID id;

    @Override
    public void execute() {
        try {
            System.out.println( "Enter the id of completed order : " );
            id = UUID.fromString( reader.readLine() );

            controller.completeOrder( id );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

    }
}
