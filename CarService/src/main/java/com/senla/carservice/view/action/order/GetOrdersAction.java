package com.senla.carservice.view.action.order;

public class GetOrdersAction extends AbstractOrderAction{
    @Override
    public void execute() {
        System.out.println("Orders registered in database: ");
        controller.getOrders().stream()
                .forEach( System.out::println );
    }
}
