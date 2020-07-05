package com.senla.carservice.view.menu;



import dependency.injection.annotations.Autowired;
import dependency.injection.annotations.components.Component;

import java.io.IOException;
@Component
public class MenuController {
    @Autowired
    private Navigator navigator;
    @Autowired
    private Builder builder ;


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
