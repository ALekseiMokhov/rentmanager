package view.action.master;

import com.senla.carservice.controller.MasterController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.scanner.ConsoleScanner;
import view.action.IAction;

import java.io.BufferedReader;

@Slf4j
@Component
public abstract class AbstracttMasterAction implements IAction {

    BufferedReader reader = ConsoleScanner.getInstance().getReader();


}
