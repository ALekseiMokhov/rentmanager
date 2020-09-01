package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class DeleteOrderAction extends AbstractOrderAction {
    @Autowired
    private OrderController controller;

    @Override
    public void execute() {
        System.out.println( "Enter UUID of the Order to delete: " );
        try {
            UUID id = UUID.fromString( reader.readLine() );
            controller.deleteOrder( id );
            System.out.println( "Order with id: " + id + " was successfully deleted!" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
