package view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.entities.master.Speciality;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GetSpecialitiesAction extends AbstracttMasterAction {
    private Set <Speciality> availableSpecialities = Set.of( Speciality.values() );
    @Autowired
    MasterController controller;
    @Override
    public void execute() {
        for (Speciality availableSpeciality : availableSpecialities) {
            System.out.println( availableSpeciality );
        }
    }
}
