package com.senla.carservice.view.action.place;

public class ExportPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        controller.exportPlacesToCsv();
    }
}
