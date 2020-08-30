package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreeMasterAction extends AbstractMasterAction {
    private LocalDate date;
    private IMaster master;
    private Speciality speciality;

    @Override
    public void execute() {

        System.out.println( "Enter speciality: " );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
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
