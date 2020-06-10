package view;

public class MenuItem {
    private String title;
    private IAction  action;
    private Menu nextMenu;

    public void doAction(){
        this.action.execute(  );
    }
}
