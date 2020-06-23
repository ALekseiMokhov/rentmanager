package com.senla.carservice.view.action.master;

import util.csv.CsvMasterWriter;
import util.csv.CsvPlaceWriter;

import java.io.IOException;

public class ExportMastersAction extends AbstractMasterAction{
    @Override
    public void execute() {
        try {
            CsvMasterWriter.writeMasters( controller.getMastersByAlphabet() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
