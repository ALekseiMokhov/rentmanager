package com.senla.carservice.controller;


import com.senla.carservice.view.menu.Builder;
import com.senla.carservice.view.menu.Navigator;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import property.configurer.PropertyInjector;

import java.io.IOException;

@Controller
public class MenuController {
    private Navigator navigator;
    @Getter
    private Builder builder;
    private PropertyInjector propertyInjector;

    public void run() throws IOException {

        builder = new Builder();
        propertyInjector = new PropertyInjector();
        propertyInjector.injectProperty( builder );


        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }


}


