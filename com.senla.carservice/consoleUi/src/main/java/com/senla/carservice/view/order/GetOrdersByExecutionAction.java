package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByExecutionAction extends AbstractOrderAction {
    private LocalDate finishOfExecution;
    @Autowired
    private OrderController controller;

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
