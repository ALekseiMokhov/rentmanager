package com.senla.carservice.view.menu;

import com.senla.carservice.view.action.IAction;
import com.senla.carservice.view.action.basic.Exit;
import com.senla.carservice.view.action.basic.Greeting;
import com.senla.carservice.view.action.basic.ShortMenu;
import com.senla.carservice.view.action.master.*;
import com.senla.carservice.view.action.order.*;
import com.senla.carservice.view.action.place.*;

public class Builder {

    private static Builder INSTANCE;

    private Builder() {

    }

    public static Builder getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Builder();
        }
        return INSTANCE;
    }


    public Menu buildMenu() {
        Menu menu = new Menu();
        addItem( menu, new Exit(), "exit" );
        addItem( menu, new Greeting(), "greeting" );
        addItem( menu, new AddPlacesAction(), "add Places to Garage" );
        addItem( menu, new AddMasterAction(), "add a new Master" );
        addItem( menu, new AddOrderAction(), "add a new Order" );

        addItem( menu, new GetPlacesAction(), "show all places in Garage" );
        addItem( menu, new GetFreePlaceAction(), " find a free place for the Date" );
        addItem( menu, new GetFreePlacesAction(), "find all free places for the Date" );
        addItem( menu, new IsPlaceSetAction(), "check whether the Place booked for the Date" );
        addItem( menu, new SavePlaceAction(), "save the Place to DB" );
        addItem( menu, new BookPlaceForDateAction(), "book the Place for the Date" );
        addItem( menu, new UnbookPlaceAction(), "unbook the Place for the Date" );
        addItem( menu, new SetPlaceIdAction(), "set custom id for the Place" );

        addItem( menu, new BookMasterAction(), "book the Master for the Date" );
        addItem( menu, new UnbookMasterAction(), "unbook the Master for the Date" );
        addItem( menu, new IsMasterBookedAction(), "check whether the Master booked for the Date" );
        addItem( menu, new RemoveMasterAction(), "delete Master from DB" );
        addItem( menu, new GetByNameAndSpecialityAction(), "find Master by his name and speciality" );
        addItem( menu, new GetFreeMasterAction(), "find a free Master of chosen speciality for the Date" );
        addItem( menu, new GetFreeMastersAction(), "find all free Master for the Date" );
        addItem( menu, new GetMasterByIdAction(), "find Master by id" );
        addItem( menu, new GetMastersByAlphabetAction(), "find all Master sorted by alphabet" );
        addItem( menu, new GetMastersBySpecialityAction(), "find all Master of chosen speciality" );
        addItem( menu, new GetSpecialitiesAction(), "find all available specialities" );

        addItem(menu, new CancelOrderAction(),"cancel chosen Order" );
        addItem(menu, new CompleteOrderAction(),"complete chosen Order" );
        addItem(menu, new FindOrderByIdAction(),"find order by id" );
        addItem(menu, new GetOrdersAction(),"find all orders" );
        addItem(menu, new GetOrdersByBookingAction(),"find all orders of chosen status filtered by booking date" );
        addItem(menu, new GetOrdersByExecutionAction(),"find all executed orders of chosen date" );
        addItem(menu, new GetOrdersForPeriodAction(),"find all orders for specific period" );
        addItem(menu, new SetNewMastersAction(),"set new masters for the order" );
        addItem(menu, new ShiftDateAction(),"move on date of execution of the chosen order" );

        addItem(menu, new ShortMenu(),"pointer to the exit and the root menu" );

        return menu;
    }

    public void addItem(Menu menu, IAction action, String title) {
        menu.getMenuItems()
                .add( new MenuItem( action, title ) );
    }


}
