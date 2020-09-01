package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.master.Speciality;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GetSpecialitiesAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
    private Set <Speciality> availableSpecialities = Set.of( Speciality.values() );

    @Override
    public void execute() {
        for (Speciality availableSpeciality : availableSpecialities) {
            System.out.println( availableSpeciality );
        }
    }
}
