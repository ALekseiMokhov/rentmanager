package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class SetNewMastersAction extends AbstractOrderAction {
    private UUID id;

    @Override
    public void execute() {
        System.out.println( "Enter the id of the order: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.setNewMasters( id );
        System.out.println( "New masters serving your order are: " + controller.findOrderById( id ).getMasters() );
    }
}
