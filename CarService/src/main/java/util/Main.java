package util;

import com.senla.carservice.view.action.basic.Greeting;
import com.senla.carservice.view.menu.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       MenuController controller = new MenuController();
       controller.run();
    }
}
