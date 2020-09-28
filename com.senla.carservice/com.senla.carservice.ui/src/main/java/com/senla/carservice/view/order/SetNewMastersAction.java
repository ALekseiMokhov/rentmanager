package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class SetNewMastersAction extends AbstractOrderAction {
    private UUID id;
    @Autowired
    private OrderController controller;

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
