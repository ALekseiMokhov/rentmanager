package com.senla.carservice.view.master;


import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreeMasterAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
    private LocalDate date;
    private com.senla.carservice.master.IMaster master;
    private com.senla.carservice.master.Speciality speciality;

    @Override
    public void execute() {

        System.out.println( "Enter speciality: " );
        try {
            speciality = com.senla.carservice.master.Speciality.valueOf( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "There is no chosen speciality available!" );
        }

        try {
            System.out.println( "Enter the date: " );
            date = LocalDate.parse( reader.readLine() );

            master = controller.getFreeBySpeciality( date, speciality );
            System.out.println( "master is: " + master );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have format 'YYYY-MM-DD'!" );
        }


    }
}
