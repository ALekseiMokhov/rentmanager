package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;

public class GetByNameAndSpecialityAction extends AbstractMasterAction {
    private String fullName;
    private IMaster master;
    Speciality speciality;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter full name of the Master: " );
            fullName = reader.readLine();

            System.out.println( "Enter speciality: " );
            speciality = Speciality.valueOf( reader.readLine() );

            master = this.controller.getByNameAndSpeciality( fullName, speciality );
            System.out.println( "Master found : " + master );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "There is no chosen speciality available!" );
        }


    }
}
