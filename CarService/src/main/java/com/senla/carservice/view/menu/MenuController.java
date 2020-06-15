package com.senla.carservice.view.menu;

import java.io.IOException;

public class MenuController {
    private Navigator navigator = Navigator.getINSTANCE();
    private Builder builder = Builder.getINSTANCE();
    private Menu rootMenu;



    public void run() throws IOException {
      rootMenu = builder.buildMenu();
       navigator.navigate( this.rootMenu,1 );
    }
}
