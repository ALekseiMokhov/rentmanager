package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByExecutionAction extends AbstractOrderAction {
    @Override
    public void execute() {


        System.out.println( "Enter the date of execution: " );

        try {
            finishOfExecution = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        List <Order> executedOrders = controller.getOrdersByExecutionDate( OrderStatus.COMPLETED ).stream()
                .filter( o -> o.getFinishOfExecution().equals( finishOfExecution ) )
                .peek( System.out::print )
                .collect( Collectors.toList() );
    }
}
