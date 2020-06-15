package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;

public class GetMastersBySpecialityAction extends AbstractMasterAction {
    @Override
    public void execute() {

        System.out.println( "Enter speciality: " );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller.getMastersBySpeciality( speciality )
                .stream()
                .forEach( System.out::println );

    }
}
