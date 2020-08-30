package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByExecutionAction extends AbstractOrderAction {
    private LocalDate finishOfExecution;

    @Override
    public void execute() {

        try {

            System.out.println( "Enter the date of execution: " );
            finishOfExecution = LocalDate.parse( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have proper format" );
        }

        List <Order> executedOrders = controller.getOrdersByExecutionDate( OrderStatus.COMPLETED ).stream()
                .filter( o -> o.getFinishOfExecution().equals( finishOfExecution ) )
                .peek( System.out::print )
                .collect( Collectors.toList() );
    }
}
