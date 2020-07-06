package com.senla.carservice.view.menu;


import com.senla.carservice.domain.service.IConfigService;

import java.io.IOException;

public class MenuController {
    private IConfigService configService;

    private Navigator navigator;
    private Builder builder;

    private Boolean isGarageModificationPermitted;
    private Boolean isMasterModificationPermitted;
    private Boolean isOrderModificationPermitted;


    public void run() throws IOException {
        requireAccessRights();
        builder.setGarageModificationPermitted( isGarageModificationPermitted );
        builder.setMasterModificationPermitted( isMasterModificationPermitted );
        builder.setOrderModificationPermitted( isOrderModificationPermitted );
        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }


    public void requireAccessRights() {
        configService.loadDefaultProps();
        isGarageModificationPermitted = Boolean.valueOf( configService.get( "garage.admin.mode" ) );
        isMasterModificationPermitted = Boolean.valueOf( configService.get( "master.admin.mode" ) );
        isOrderModificationPermitted = Boolean.valueOf( configService.get( "order.admin.mode" ) );
    }
}


