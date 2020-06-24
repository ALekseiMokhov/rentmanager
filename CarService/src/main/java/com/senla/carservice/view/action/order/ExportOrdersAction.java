package com.senla.carservice.view.action.order;

import util.csv.CsvOrderWriter;

import java.io.IOException;

public class ExportOrdersAction extends AbstractOrderAction {
    @Override
    public void execute() {
        try {
            CsvOrderWriter.writeOrders( controller.getOrders() );
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println( controller.getOrders().size() + " orders were successfully written to csv file!" );
    }

}
