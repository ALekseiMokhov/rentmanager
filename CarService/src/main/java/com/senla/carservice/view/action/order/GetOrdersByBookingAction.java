package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.OrderStatus;

import java.io.IOException;

public class GetOrdersByBookingAction extends AbstractOrderAction {
    private OrderStatus status;

    @Override
    public void execute() {


        try {
            System.out.println( "Enter the status of orders : " );
            status = OrderStatus.valueOf( reader.readLine() );

            controller.getOrdersByBookedDate( status ).stream()
                    .forEach( o -> System.out.println( "order id: " + o.getId()
                            + " date of booking: " + o.getDateBooked() ) );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "Status could be MANAGED,CANCELLED or COMPLETED!" );
        }


    }
}
