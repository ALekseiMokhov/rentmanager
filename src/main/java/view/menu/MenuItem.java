package view.menu;

import lombok.Data;
import lombok.Getter;
import view.action.IAction;

import java.io.IOException;

@Data
public class MenuItem {
    @Getter
    private IAction action;
    private String title;
    private Boolean isAccessPermitted;

    public MenuItem(view.action.IAction action, String title, Boolean isAccessPermitted) {
        this.title = title;
        this.action = action;
        this.isAccessPermitted = isAccessPermitted;
    }

    public void doAction() throws IOException {

        action.execute();
    }

}
