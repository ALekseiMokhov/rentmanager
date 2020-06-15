package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class CancelOrderAction extends AbstractOrderAction {
    @Override
    public void execute() {
        System.out.println( "Enter the id of order to cancel : " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.cancelOrder( id );
    }
}
