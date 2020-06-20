package util;


import com.senla.carservice.view.action.master.LoadExternalMastersAction;
import com.senla.carservice.view.menu.MenuController;
import util.csv.CsvMasterParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        MenuController controller = new MenuController();
        controller.run();
     /*   LoadExternalMastersAction action = new LoadExternalMastersAction();
        action.execute();*/

    }
}
