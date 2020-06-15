package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.util.UUID;

public class FindOrderByIdAction extends AbstractOrderAction {
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
