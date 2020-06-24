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
        System.out.println( "Enter full name of the Master: " );
        try {
            fullName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Enter speciality: " );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        master = this.controller.getByNameAndSpeciality( fullName, speciality );
        System.out.println( "Master found : " + master );
    }
}
