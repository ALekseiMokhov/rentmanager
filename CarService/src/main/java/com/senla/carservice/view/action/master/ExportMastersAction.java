package com.senla.carservice.view.action.master;

import util.csv.CsvMasterWriter;

import java.io.IOException;

public class ExportMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {
        try {
            CsvMasterWriter.writeMasters( controller.getMastersByAlphabet() );
            System.out.println( controller.getMastersByAlphabet().size() + " masters were successfully written to csv file!" );
        } catch (IOException e) {
            System.err.println( "Check a path to the file!" );

        }

    }
}
