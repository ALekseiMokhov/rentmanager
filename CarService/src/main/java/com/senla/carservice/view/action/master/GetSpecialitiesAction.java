package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

import java.util.Set;

public class GetSpecialitiesAction extends AbstractMasterAction {
    private Set <Speciality> availableSpecialities = Set.of( Speciality.values() );

    @Override
    public void execute() {
        for (Speciality availableSpeciality : availableSpecialities) {
            System.out.println( availableSpeciality );
        }
    }
}
