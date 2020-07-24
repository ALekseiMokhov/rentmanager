package com.senla.carservice.controller;


import com.senla.carservice.view.menu.Builder;
import com.senla.carservice.view.menu.Navigator;
import dependency.injection.annotations.components.Component;
import dependency.injection.annotations.scope.Singleton;

import java.io.IOException;

@Component

public class MenuController {

    private Navigator navigator;
    private Builder builder;

    public void run() throws IOException {


        builder = new Builder();

        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }


}


