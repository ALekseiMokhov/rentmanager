package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreeMasterAction extends AbstractMasterAction {
    @Override
    public void execute() {

        System.out.println( "Enter speciality: " );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println( "Enter the date: " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }

        master = controller.getFreeBySpeciality( date, speciality );
        System.out.println( "master is: " + master );
    }
}
