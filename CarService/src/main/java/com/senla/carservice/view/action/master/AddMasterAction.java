package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;
import util.Calendar;

import java.io.IOException;

public class AddMasterAction extends AbstractMasterAction {
    private String fullName;
    private double salary;
    private Speciality speciality;

    @Override
    public void execute() {


        System.out.println( "Enter full name of new Master: " );

        try {
            fullName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Enter a daily salary of new Master: " );
        try {
            salary = Double.parseDouble( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "Enter speciality of new Master ( " );
        System.out.println( " available specialities are: " );
        System.out.print( controller.getAvailableSpecialities() + " ) :" );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.addMaster( fullName, salary, new Calendar(), speciality );
        System.out.println( "Master " +
                controller.getByNameAndSpeciality( fullName, speciality ) + " was successfully added!" );
    }
}
