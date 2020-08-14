package com.senla.carservice.view.menu;


import com.senla.carservice.view.action.IAction;
import com.senla.carservice.view.action.basic.*;
import com.senla.carservice.view.action.master.*;
import com.senla.carservice.view.action.order.*;
import com.senla.carservice.view.action.place.*;
import lombok.Getter;
import property.configurer.annotations.ConfigProperty;

public class Builder {
    @ConfigProperty(propertyName = "garage.admin.mode", type = Boolean.class)
    private Boolean isGarageModificationPermitted;
    @ConfigProperty(propertyName = "master.admin.mode", type = Boolean.class)
    private Boolean isMasterModificationPermitted;
    @ConfigProperty(propertyName = "order.admin.mode", type = Boolean.class)
    private Boolean isOrderModificationPermitted;
    @ConfigProperty(propertyName = "json.persistence.mode", type = Boolean.class)
    @Getter
    private Boolean isJsonPersistenceAllowed;

    public Menu buildRootMenu() {
        Menu menu = new Menu();
        menu.setName( "Root menu" );
        addItem( menu, new RootMenuItem(), "Root menu", true );
        addItem( menu, new PlaceMenuItem(), "Place menu", true );
        addItem( menu, new MasterMenuItem(), "Master menu", true );
        addItem( menu, new OrderMenuItem(), "Order menu", true );
        addItem( menu, new ExitItem(), "Exit", true );
        return menu;
    }

    public Menu buildPlaceMenu() {
        Menu placeMenu = new Menu();
        placeMenu.setName( "Place menu" );
        addItem( placeMenu, new PlaceMenuItem(), "Displaying menu", true );
        addItem( placeMenu, new AddPlacesAction(), "Add place", isGarageModificationPermitted );
        addItem( placeMenu, new GetPlacesAction(), "show all places in Garage", true );
        addItem( placeMenu, new GetFreePlaceAction(), " find a free place for the Date", true );
        addItem( placeMenu, new GetFreePlacesAction(), "find all free places for the Date", true );
        addItem( placeMenu, new IsPlaceSetAction(), "check whether the Place booked for the Date", true );
        addItem( placeMenu, new SavePlaceAction(), "save the Place to DB", isGarageModificationPermitted );
        addItem( placeMenu, new BookPlaceForDateAction(), "book the Place for the Date", true );
        addItem( placeMenu, new UnbookPlaceAction(), "unbook the Place for the Date", true );
        addItem( placeMenu, new SetPlaceIdAction(), "set custom id for the Place", isGarageModificationPermitted );
        addItem( placeMenu, new ImportPlacesAction(), "load places to csv file", isGarageModificationPermitted );
        addItem( placeMenu, new ExportPlacesAction(), "load places from csv file", isGarageModificationPermitted );
        addItem( placeMenu, new RootMenuItem(), "Root menu", true );
        addItem( placeMenu, new ExitItem(), "Exit", true );


        return placeMenu;
    }

    public Menu buildMasterMenu() {
        Menu masterMenu = new Menu();
        masterMenu.setName( "Master menu" );

        addItem( masterMenu, new MasterMenuItem(), "Displaying menu", true );
        addItem( masterMenu, new AddMasterAction(), "Add master", isMasterModificationPermitted );
        addItem( masterMenu, new BookMasterAction(), "book the Master for the Date", true );
        addItem( masterMenu, new UnbookMasterAction(), "unbook the Master for the Date", true );
        addItem( masterMenu, new IsMasterBookedAction(), "check whether the Master booked for the Date", true );
        addItem( masterMenu, new RemoveMasterAction(), "delete Master from DB", isMasterModificationPermitted );
        addItem( masterMenu, new GetByNameAndSpecialityAction(), "find Master by his name and speciality", true );
        addItem( masterMenu, new GetFreeMasterAction(), "find a free Master of chosen speciality for the Date", true );
        addItem( masterMenu, new GetFreeMastersAction(), "find all free Master for the Date", true );
        addItem( masterMenu, new GetMasterByIdAction(), "find Master by id", true );
        addItem( masterMenu, new GetMastersByAlphabetAction(), "find all Master sorted by alphabet", true );
        addItem( masterMenu, new GetMastersBySpecialityAction(), "find all Master of chosen speciality", true );
        addItem( masterMenu, new GetSpecialitiesAction(), "find all available specialities", true );
        addItem( masterMenu, new ExportMastersAction(), "load masters to csv file", isMasterModificationPermitted );
        addItem( masterMenu, new ImportMastersAction(), "load masters from csv file", isMasterModificationPermitted );
        addItem( masterMenu, new RootMenuItem(), "Root menu", true );
        addItem( masterMenu, new ExitItem(), "Exit", true );


        return masterMenu;
    }

    public Menu buildOrderMenu() {
        Menu orderMenu = new Menu();
        orderMenu.setName( "Order menu" );
        addItem( orderMenu, new OrderMenuItem(), "Displaying menu", true );
        addItem( orderMenu, new AddOrderAction(), "Add order", isOrderModificationPermitted );
        addItem( orderMenu, new CancelOrderAction(), "cancel chosen Order", true );
        addItem( orderMenu, new CompleteOrderAction(), "complete chosen Order", true );
        addItem( orderMenu, new FindOrderByIdAction(), "find order by id", true );
        addItem( orderMenu, new GetOrdersAction(), "find all orders", true );
        addItem( orderMenu, new GetOrdersByBookingAction(), "find all orders of chosen status filtered by booking date", true );
        addItem( orderMenu, new GetOrdersByExecutionAction(), "find all executed orders of chosen date", true );
        addItem( orderMenu, new GetOrdersForPeriodAction(), "find all orders for specific period", true );
        addItem( orderMenu, new SetNewMastersAction(), "set new masters for the order", true );
        addItem( orderMenu, new ShiftDateAction(), "move on date of execution of the chosen order", isOrderModificationPermitted );
        addItem( orderMenu, new ExportOrdersAction(), "load orders to csv file", isOrderModificationPermitted );
        addItem( orderMenu, new ImportOrdersAction(), "load orders from csv file", isOrderModificationPermitted );
        addItem( orderMenu, new RootMenuItem(), "Root menu", true );
        addItem( orderMenu, new ExitItem(), "Exit", true );

        return orderMenu;
    }

    public Menu buildAccessMenu() {
        Menu accessMenu = new Menu();
        accessMenu.setName( "Access menu" );
        addItem( accessMenu, new AccessMenuItem(), "Permission to data modification", true );
        addItem( accessMenu, new RootMenuItem(), "Root menu", true );
        addItem( accessMenu, new ExitItem(), "Exit", true );

        return accessMenu;
    }


    private void addItem(Menu menu, IAction action, String title, Boolean isAccessPermitted) {
        menu.getMenuItems()
                .add( new MenuItem( action, title, isAccessPermitted ) );
    }


}
