package view.action.place;

import com.senla.carservice.controller.PlaceController;

import org.springframework.beans.factory.annotation.Autowired;
import util.scanner.ConsoleScanner;
import view.action.IAction;

import java.io.BufferedReader;


public abstract class AbstractPlaceAction implements IAction {

    protected BufferedReader reader = ConsoleScanner.getInstance().getReader();


}
