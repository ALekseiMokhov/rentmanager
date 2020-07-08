package com.senla.carservice.view.menu;



import java.io.IOException;

public class MenuController {
    private Navigator navigator;
    private Builder builder = Builder.getInstance();


    public void run() throws IOException {
        builder.requireAccessRights();
        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }
}
