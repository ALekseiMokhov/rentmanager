package com.senla.carservice.view.action.master;

public class ImportMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {
        controller.loadMastersFromCsv();

    }
}
