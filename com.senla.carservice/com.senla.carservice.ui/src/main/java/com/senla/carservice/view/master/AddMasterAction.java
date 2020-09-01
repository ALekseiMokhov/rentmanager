package com.senla.carservice.view.master;


import com.senla.carservice.controller.MasterController;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AddMasterAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
    private String fullName;
    private double salary;
    private Speciality speciality;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter full name of new Master: " );
            fullName = reader.readLine();

            System.out.println( "Enter a daily salary of new Master: " );
            salary = Double.parseDouble( reader.readLine() );

        } catch (IOException | IllegalArgumentException e) {

            System.err.println( "\n" + "Salary should be made of digits!" );
        }
        System.out.println( "Enter speciality of the new Master ( " );
        System.out.println( "Available specialities are: " );
        System.out.print( controller.getAvailableSpecialities() + " ) :" );

        try {
            speciality = Speciality.valueOf( reader.readLine() );
            controller.addMaster( fullName, salary, new Calendar(), speciality );
            System.out.println( "Master " + fullName + " was successfully added!" );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "Speciality you required for doesn't exist!" );

        }

    }
}
