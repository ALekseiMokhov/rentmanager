package view.action.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportMastersAction extends AbstracttMasterAction {
    @Autowired
    MasterController controller;
    @Override
    public void execute() {
        controller.exportMastersToCsv();

    }
}
