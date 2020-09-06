package view.action.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

public class GetMastersByAlphabetAction extends AbstracttMasterAction {
    @Autowired
    MasterController controller;
    @Override
    public void execute() {

        System.out.println( " List of masters by alphabet: " );
        this.controller.getMastersByAlphabet().stream()
                .forEach( System.out::println );
    }
}
