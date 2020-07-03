package com.senla.carservice.view.action.place;

public class ImportPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
       controller.loadPlacesFromCsv();

    }
}
