package com.senla.carservice.view.menu;

import com.senla.carservice.view.action.IAction;
import com.senla.carservice.view.action.basic.*;
import com.senla.carservice.view.action.master.*;
import com.senla.carservice.view.action.order.*;
import com.senla.carservice.view.action.place.*;

public class Builder {

    private static Builder instance;

    private Builder() {

    }

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }


    public Menu buildRootMenu() {
        Menu menu = new Menu();
        menu.setName( "Root menu" );
        addItem( menu, new RootMenuItem(), "Root menu" );
        addItem( menu, new PlaceMenuItem(), "Place menu" );
        addItem( menu, new MasterMenuItem(), "Master menu" );
        addItem( menu, new OrderMenuItem(), "Order menu" );
        addItem( menu, new ExitItem(), "Exit" );
        return menu;
    }

    public Menu buildPlaceMenu() {
        Menu placeMenu = new Menu();
        placeMenu.setName( "Place menu" );
        addItem( placeMenu, new PlaceMenuItem(), "Displaying menu" );
        addItem( placeMenu, new AddPlacesAction(), "Add place" );
        addItem( placeMenu, new GetPlacesAction(), "show all places in Garage" );
        addItem( placeMenu, new GetFreePlaceAction(), " find a free place for the Date" );
        addItem( placeMenu, new GetFreePlacesAction(), "find all free places for the Date" );
        addItem( placeMenu, new IsPlaceSetAction(), "check whether the Place booked for the Date" );
        addItem( placeMenu, new SavePlaceAction(), "save the Place to DB" );
        addItem( placeMenu, new BookPlaceForDateAction(), "book the Place for the Date" );
        addItem( placeMenu, new UnbookPlaceAction(), "unbook the Place for the Date" );
        addItem( placeMenu, new SetPlaceIdAction(), "set custom id for the Place" );
        addItem( placeMenu, new LoadExternalPlacesAction(),"load places from csv file" );
        addItem( placeMenu, new RootMenuItem(), "Root menu" );
        addItem( placeMenu, new ExitItem(), "Exit" );


        return placeMenu;
    }

    public Menu buildMasterMenu() {
        Menu masterMenu = new Menu();
        masterMenu.setName( "Master menu" );

        addItem( masterMenu, new MasterMenuItem(), "Displaying menu" );
        addItem( masterMenu, new AddMasterAction(), "Add master" );
        addItem( masterMenu, new BookMasterAction(), "book the Master for the Date" );
        addItem( masterMenu, new UnbookMasterAction(), "unbook the Master for the Date" );
        addItem( masterMenu, new IsMasterBookedAction(), "check whether the Master booked for the Date" );
        addItem( masterMenu, new RemoveMasterAction(), "delete Master from DB" );
        addItem( masterMenu, new GetByNameAndSpecialityAction(), "find Master by his name and speciality" );
        addItem( masterMenu, new GetFreeMasterAction(), "find a free Master of chosen speciality for the Date" );
        addItem( masterMenu, new GetFreeMastersAction(), "find all free Master for the Date" );
        addItem( masterMenu, new GetMasterByIdAction(), "find Master by id" );
        addItem( masterMenu, new GetMastersByAlphabetAction(), "find all Master sorted by alphabet" );
        addItem( masterMenu, new GetMastersBySpecialityAction(), "find all Master of chosen speciality" );
        addItem( masterMenu, new GetSpecialitiesAction(), "find all available specialities" );
        addItem( masterMenu, new LoadExternalMastersAction(),"load masters from csv file" );
        addItem( masterMenu, new RootMenuItem(), "Root menu" );
        addItem( masterMenu, new ExitItem(), "Exit" );
        return masterMenu;
    }

    public Menu buildOrderMenu() {
        Menu orderMenu = new Menu();
        orderMenu.setName( "Order menu" );
        addItem( orderMenu,new OrderMenuItem(),"Displaying menu" );
        addItem( orderMenu, new AddOrderAction(), "Add order" );
        addItem( orderMenu, new CancelOrderAction(), "cancel chosen Order" );
        addItem( orderMenu, new CompleteOrderAction(), "complete chosen Order" );
        addItem( orderMenu, new FindOrderByIdAction(), "find order by id" );
        addItem( orderMenu, new GetOrdersAction(), "find all orders" );
        addItem( orderMenu, new GetOrdersByBookingAction(), "find all orders of chosen status filtered by booking date" );
        addItem( orderMenu, new GetOrdersByExecutionAction(), "find all executed orders of chosen date" );
        addItem( orderMenu, new GetOrdersForPeriodAction(), "find all orders for specific period" );
        addItem( orderMenu, new SetNewMastersAction(), "set new masters for the order" );
        addItem( orderMenu, new ShiftDateAction(), "move on date of execution of the chosen order" );
        addItem( orderMenu, new LoadExternalOrdersAction(),"load orders from csv file" );
        addItem( orderMenu, new RootMenuItem(), "Root menu" );
        addItem( orderMenu, new ExitItem(), "Exit" );

        return orderMenu;
    }

    private void addItem(Menu menu, IAction action, String title) {
        menu.getMenuItems()
                .add( new MenuItem( action, title ) );
    }


}
