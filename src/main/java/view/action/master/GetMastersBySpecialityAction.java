package view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.entities.master.Speciality;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GetMastersBySpecialityAction extends AbstracttMasterAction {
    @Autowired
    MasterController controller;
    private Speciality speciality;

    @Override
    public void execute() {


        try {
            System.out.println( "Enter speciality: " );
            speciality = Speciality.valueOf( reader.readLine() );

            this.controller.getMastersBySpeciality( speciality )
                    .stream()
                    .forEach( System.out::println );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "Speciality of provided type doesn't exist!" );
        }

    }
}
