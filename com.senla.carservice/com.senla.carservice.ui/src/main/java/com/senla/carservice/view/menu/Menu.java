package com.senla.carservice.view.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private List <MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList <>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List <MenuItem> getMenuItems() {
        return menuItems;
    }

}
