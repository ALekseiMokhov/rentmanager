package com.senla.carservice.view.action.master;

public class ExportMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {
        controller.exportMastersToCsv();

    }
}
