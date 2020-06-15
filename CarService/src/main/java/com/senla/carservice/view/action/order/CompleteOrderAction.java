package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class CompleteOrderAction extends AbstractOrderAction {
    @Override
    public void execute() {
        System.out.println("Enter the id of completed order : ");
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.completeOrder( id );
    }
}
