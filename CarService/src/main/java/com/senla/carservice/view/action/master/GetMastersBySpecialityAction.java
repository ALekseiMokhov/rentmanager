package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;

public class GetMastersBySpecialityAction extends AbstractMasterAction {

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
