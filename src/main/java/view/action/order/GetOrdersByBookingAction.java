package view.action.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.entities.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GetOrdersByBookingAction extends AbstractOrderAction {
    private OrderStatus status;
    @Autowired
    private OrderController controller;
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
