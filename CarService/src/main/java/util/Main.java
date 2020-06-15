package util;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.repository.PlaceRepository;
import com.senla.carservice.view.menu.MenuController;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

       MenuController controller = new MenuController();
       controller.run();
    }
}
