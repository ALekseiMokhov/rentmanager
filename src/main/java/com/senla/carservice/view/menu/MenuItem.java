package com.senla.carservice.view.menu;

import com.senla.carservice.view.action.IAction;

import java.io.IOException;

public class MenuItem {

    private IAction action;
    private String title;
    private Boolean isAccessPermitted;

    public MenuItem(IAction action, String title, Boolean isAccessPermitted) {
        this.title = title;
        this.action = action;
        this.isAccessPermitted = isAccessPermitted;
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

    public Boolean getAccessPermitted() {
        return isAccessPermitted;
    }

    public void setAccessPermitted(Boolean accessPermitted) {
        isAccessPermitted = accessPermitted;
    }

    public void doAction() throws IOException {

        action.execute();
    }
}
