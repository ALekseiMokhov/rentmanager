package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;
import util.csv.CsvMasterParser;

import java.io.IOException;
import java.util.List;

public class ImportMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {
        try {
          List<IMaster>list = CsvMasterParser.load();
            for (IMaster master : list) {
                 controller.loadMaster( master );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
