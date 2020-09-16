package com.senla.carservice.view.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

public class ImportOrdersAction extends AbstractOrderAction {
    @Autowired
    private OrderController controller;

    @Override
    public void execute() {
        controller.loadFromCsv();
    }
}
