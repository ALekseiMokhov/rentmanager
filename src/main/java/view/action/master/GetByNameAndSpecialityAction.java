package view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GetByNameAndSpecialityAction extends AbstracttMasterAction {
    @Autowired
    MasterController controller;
    Speciality speciality;
    private String fullName;
    private IMaster master;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter full name of the Master: " );
            fullName = reader.readLine();

            System.out.println( "Enter speciality: " );
            speciality = Speciality.valueOf( reader.readLine() );

            master = this.controller.getByNameAndSpeciality( fullName, speciality );
            System.out.println( "Master found : " + master );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "There is no chosen speciality available!" );
        }


    }
}
