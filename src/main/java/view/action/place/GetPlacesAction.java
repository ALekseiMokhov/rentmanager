package view.action.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.domain.entities.garage.Place;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetPlacesAction extends AbstractPlaceAction {
    @Autowired
    private PlaceController controller;
    @Override
    public void execute() {
        System.out.println(
                "All places in Garage: "
        );

        List <Place> list = this.controller.getPlaces();
        list.stream()
                .forEach( System.out::println );

    }
}
