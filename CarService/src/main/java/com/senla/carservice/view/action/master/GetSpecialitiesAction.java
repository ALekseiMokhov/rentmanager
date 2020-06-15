package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;

public class GetSpecialitiesAction extends AbstractMasterAction {
    @Override
    public void execute() {
        for (Speciality availableSpeciality : availableSpecialities) {
            System.out.println( availableSpeciality );
        }
    }
}
