
package com.senla.carservice.view.menu;

import com.senla.carservice.view.action.IAction;

import java.io.IOException;

public class MenuItem {

    private IAction action;
    private String title;

    public MenuItem(IAction action, String title) {
        this.title = title;
        this.action = action;

    }


    public IAction getAction() {
        return action;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }


    public void doAction() throws IOException {
        action.execute();
    }
}
