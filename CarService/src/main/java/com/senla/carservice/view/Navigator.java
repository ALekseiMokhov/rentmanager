package view;

public class Navigator {
    private Menu current;
    private static Navigator INSTANCE;
    private Navigator() {

    }

    public static Navigator getINSTANCE() {
        if(INSTANCE==null){
            INSTANCE=new Navigator();
        }
        return INSTANCE;
    }

    public void navigate(Integer index){

    }
    public void print()  {
        System.out.println(current.getName());
    }

    public Menu getCurrent() {
        return current;
    }

    public void setCurrent(Menu current) {
        this.current = current;
    }
}
