package com.senla.carservice.view.menu;

import util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;

public class Navigator {

    private int currentIndex;
    private BufferedReader reader;
    private boolean isExit ;
    private static Navigator INSTANCE;

    
    private Navigator() {
       this.reader = Scanner.getInstance().getReader();

    }

    public static Navigator getINSTANCE() {
        if(INSTANCE==null){
            INSTANCE=new Navigator();
        }
        return INSTANCE;
    }
    public void navigate(Menu rootMenu, Integer index) throws IOException {
        rootMenu.getMenuItems().get( 1 ).doAction();
        while (!isExit) {
            try {

                String line = reader.readLine();
                currentIndex = Integer.parseInt(line);
                print( currentIndex );       /*TODO remove*/

                MenuItem menuItem = rootMenu.getMenuItems().get( currentIndex );
                menuItem.doAction();
                if(menuItem.getTitle().equals( "exit" ))  {
                    this.isExit = true;
                }
                MenuItem shortMenu = rootMenu.getMenuItems().get( 33 );
                shortMenu.doAction();
            } catch ( IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void print(Integer index)  {
        System.out.println("current index: "+index);
        System.out.println();
    }





}
