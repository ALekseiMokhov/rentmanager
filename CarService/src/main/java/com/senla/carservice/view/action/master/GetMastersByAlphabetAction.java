package com.senla.carservice.view.action.master;

import java.util.stream.Stream;

public class GetMastersByAlphabetAction extends AbstractMasterAction{
    @Override
    public void execute() {
        System.out.println(" List of masters by alphabet: ");
        this.controller.getMastersByAlphabet()
                .stream()
                .forEach( System.out::println );
    }
}
