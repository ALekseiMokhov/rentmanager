package com.senla.carservice.view.menu;

import com.senla.carservice.util.scanner.ConsoleScanner;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
public class Navigator {
    private int currentIndex;
    private BufferedReader reader;
    private boolean isExit;
    private Menu rootMenu;
    private Menu placeMenu;
    private Menu masterMenu;
    private Menu orderMenu;
    private Menu accessMenu;


    public Navigator(Menu rootMenu, Menu placeMenu, Menu masterMenu, Menu orderMenu, Menu accessMenu) {
        this.reader = ConsoleScanner.getInstance().getReader();
        this.rootMenu = rootMenu;
        this.placeMenu = placeMenu;
        this.masterMenu = masterMenu;
        this.orderMenu = orderMenu;
        this.accessMenu = accessMenu;
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
                    rootMenu.getMenuItems().get( 4 ).doAction();

                }
                if (menuItem.getIsAccessPermitted() == false) {
                    current = this.accessMenu;
                    navigate( current );
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


                if (!isExit) {
                    System.out.println( "Menu current: " + current.getName() );
                    System.out.println( "MenuItem current :" + menuItem.getTitle() );
                    System.out.println();
                    menuItem.doAction();
                }


            } catch (Exception e) {

                log.error( e.getMessage(), e );
                navigate( rootMenu );

            }

        }

    }

    public Menu getRootMenu() {
        return rootMenu;
    }


}
