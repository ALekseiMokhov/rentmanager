package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

public class GetMastersByAlphabetAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;

    @Override
    public void execute() {

        System.out.println( " List of masters by alphabet: " );
        this.controller.getMastersByAlphabet().stream()
                .forEach( System.out::println );
    }
}
