package util;


import com.senla.carservice.view.menu.MenuController;
import util.warning.Supressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Supressor.disableWarning();

        MenuController controller = new MenuController();
        controller.run();


    }
}
