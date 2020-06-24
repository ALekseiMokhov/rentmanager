package com.senla.carservice.view.action.order;

public class ExportOrdersAction extends AbstractOrderAction {
    @Override
    public void execute() {
       controller.exportToCsv();

    }

}
