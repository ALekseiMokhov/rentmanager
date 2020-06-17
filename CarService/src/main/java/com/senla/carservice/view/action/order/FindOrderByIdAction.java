package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.Order;

import java.io.IOException;
import java.util.UUID;

public class FindOrderByIdAction extends AbstractOrderAction {
    private Order order;
    private UUID id;

    @Override
    public void execute() {
        System.out.println( "Enter the id of order : " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        order = controller.findOrderById( id );

        System.out.println( order );
    }
}
