package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.Order;

import java.io.IOException;
import java.util.UUID;

public class FindOrderByIdAction extends AbstractOrderAction {
    private Order order;
    private UUID id;

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
