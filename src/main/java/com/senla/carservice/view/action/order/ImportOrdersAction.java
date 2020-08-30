package com.senla.carservice.view.action.order;

public class ImportOrdersAction extends AbstractOrderAction {
    @Override
    public void execute() {
        controller.loadFromCsv();
    }
}
