package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;

import com.senla.carservice.entity.master.Speciality;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GetMastersBySpecialityAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
    private Speciality speciality;

    @Override
    public void execute() {


        try {
            System.out.println( "Enter speciality: " );
            speciality = Speciality.valueOf( reader.readLine() );

            this.controller.getMastersBySpeciality( speciality )
                    .stream()
                    .forEach( System.out::println );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "Speciality of provided type doesn't exist!" );
        }

    }
}
