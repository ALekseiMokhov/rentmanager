package com.senla.carservice.view.menu;

import com.senla.carservice.view.action.IAction;
import com.senla.carservice.view.action.basic.Exit;
import com.senla.carservice.view.action.basic.Greeting;
import com.senla.carservice.view.action.place.GetFreePlacesAction;

public class Builder {

  private static Builder INSTANCE;

  private Builder() {

  }

  public static Builder getINSTANCE() {
    if(INSTANCE==null)  {
      INSTANCE = new Builder();
    }
    return INSTANCE;
  }


    public Menu buildMenu(){
     Menu menu = new Menu();
     addItem(menu, new Exit(),"exit" );
     addItem( menu, new Greeting(),"greeting" );
     addItem(menu, new GetFreePlacesAction(),"getFreePlaces" );
       return menu;
    }

    public void addItem(Menu menu, IAction action, String title){
      menu.getMenuItems()
              .add( new MenuItem(action,title ) )  ;
    }




    
}
