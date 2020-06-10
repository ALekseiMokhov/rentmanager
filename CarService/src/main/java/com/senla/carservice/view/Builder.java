package view;

public class Builder {
  private  Menu rootMenu = new Menu();
  private static Builder INSTANCE;

  private Builder() {

  }

  public static Builder getINSTANCE() {
    if(INSTANCE==null)  {
      INSTANCE = new Builder();
    }
    return INSTANCE;
  }

  public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu buildMenu(){
      return new Menu();

  }
    
}
