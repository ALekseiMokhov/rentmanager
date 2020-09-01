package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class FindOrderByIdAction extends AbstractOrderAction {
    private com.senla.carservice.order.Order order;
    private UUID id;
    @Autowired
    private OrderController controller;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter the id of order : " );
            id = UUID.fromString( reader.readLine() );

            order = controller.findOrderById( id );
            System.out.println( order );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

    }
}
