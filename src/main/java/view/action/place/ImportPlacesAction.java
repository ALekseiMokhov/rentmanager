package view.action.place;

import com.senla.carservice.controller.PlaceController;
import org.springframework.beans.factory.annotation.Autowired;

public class ImportPlacesAction extends AbstractPlaceAction {
    @Autowired
    private PlaceController controller;
    @Override
    public void execute() {
        controller.loadPlacesFromCsv();

    }
}
