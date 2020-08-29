package injection.beanfactory.exceptions;

public class BeanInitException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "Cant initialize bean!";
    }
}
