package view;

public class MenuController {
    private Navigator navigator = Navigator.getINSTANCE();
    private Builder builder = Builder.getINSTANCE();
    private Menu rootMenu = builder.getRootMenu();

    public void init(){
        rootMenu.setName( "Hello visitor!" );
        navigator.setCurrent(rootMenu);
        navigator.print();
    }

    public void run(){
       Menu menu = builder.buildMenu();
       menu.setName( "there" );
       navigator.setCurrent(menu);
       navigator.print();
    }
}
