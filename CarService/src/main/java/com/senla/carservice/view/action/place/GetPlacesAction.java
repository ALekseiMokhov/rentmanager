package com.senla.carservice.view.action.place;

public class GetPlacesAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        System.out.println(
                "All places in Garage: "
        );

        this.controller.getPlaces().stream()
                .forEach( System.out::println );

    }
}
