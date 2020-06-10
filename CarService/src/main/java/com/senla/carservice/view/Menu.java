package view;

import java.util.List;

public class Menu {
private String name;
private List <MenuItem> menuItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List <MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List <MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
