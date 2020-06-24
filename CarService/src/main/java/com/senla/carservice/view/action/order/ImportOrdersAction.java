package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.order.Order;
import util.csv.CsvOrderParser;

import java.io.IOException;
import java.util.List;

public class ImportOrdersAction extends AbstractOrderAction {
    @Override
    public void execute() {
        try {
            List <Order> orderList = CsvOrderParser.load();
            for (Order order : orderList) {
                controller.loadOrder( order );
            }
        } catch (IOException e) {
            System.err.println( "Check a path to the file!" );
        }
    }
}
