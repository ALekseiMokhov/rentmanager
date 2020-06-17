package com.senla.carservice.view.menu;

import util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;

public class Navigator {

    private int currentIndex;
    private BufferedReader reader;
    private boolean isExit;
    private Menu rootMenu;
    private Menu placeMenu;
    private Menu masterMenu;
    private Menu orderMenu;


    public Navigator(Menu rootMenu, Menu placeMenu, Menu masterMenu, Menu orderMenu) {
        this.reader = Scanner.getInstance().getReader();
        this.rootMenu = rootMenu;
        this.placeMenu = placeMenu;
        this.masterMenu = masterMenu;
        this.orderMenu = orderMenu;

    }


    public void navigate(Menu current) throws IOException {

        while (!isExit) {
            try {
                current.getMenuItems().get( 0 ).doAction();

                String line = reader.readLine();
                currentIndex = Integer.parseInt( line );


                MenuItem menuItem = current.getMenuItems().get( currentIndex );

                if (menuItem.getTitle().equals( "Exit" )) {
                    this.isExit = true;
                   
                }
                if (menuItem.getTitle().equals( "Root menu" )) {
                    current = this.rootMenu;
                    navigate( current );
                }
                if (menuItem.getTitle().equals( "Master menu" )) {
                    current = this.masterMenu;
                    navigate( current );
                }
                if (menuItem.getTitle().equals( "Place menu" )) {
                    current = this.placeMenu;
                    navigate( current );
                }
                if (menuItem.getTitle().equals( "Order menu" )) {
                    current = this.orderMenu;
                    navigate( current );
                }

                if(!isExit){
                    System.out.println( "Menu current: " + current.getName() );
                    System.out.println( "MenuItem current :" + menuItem.getTitle() );
                    System.out.println();
                menuItem.doAction();      }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public Menu getRootMenu() {
        return rootMenu;
    }


}
