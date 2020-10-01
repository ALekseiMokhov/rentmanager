package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

public class GetOrdersAction extends AbstractOrderAction {
    @Autowired
    private OrderController controller;

    @Override
    public void execute() {
        System.out.println( "Orders registered in database: " );
        controller.getOrders().stream()
                .forEach( System.out::println );
    }
}
