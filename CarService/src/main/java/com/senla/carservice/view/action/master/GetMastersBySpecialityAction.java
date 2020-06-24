package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;

public class GetMastersBySpecialityAction extends AbstractMasterAction {

    private Speciality speciality;

    @Override
    public void execute() {

        System.out.println( "Enter speciality: " );
        try {
            speciality = Speciality.valueOf( reader.readLine() );
        } catch (IOException e) {
            System.err.println("Some problems with input occur!");
        }
        catch (IllegalArgumentException e){
            System.err.println("Speciality of provided type doesn't exist!");
        }
        this.controller.getMastersBySpeciality( speciality )
                .stream()
                .forEach( System.out::println );

    }
}
