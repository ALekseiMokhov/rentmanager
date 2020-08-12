package com.senla.carservice.controller;


import com.senla.carservice.view.menu.Builder;
import com.senla.carservice.view.menu.Navigator;
import dependency.injection.annotations.components.Component;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import property.configurer.PropertyInjector;

import java.io.IOException;

@Component
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger( MenuController.class );
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


