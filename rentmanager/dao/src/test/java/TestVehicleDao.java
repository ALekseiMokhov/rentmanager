import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.RentPointDao;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.PointType;
import ru.rambler.alexeimohov.entities.enums.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class TestVehicleDao {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private RentPointDao rentPointDao;

    private Vehicle v1;

    private Vehicle v2;

    private Vehicle v3;

    private Point point;

    private RentPoint rentPoint;

    @BeforeEach
    @Transactional
    void init() {
        this.point = new GeometryFactory().createPoint(new Coordinate(25.9, 23.9));
        this.rentPoint = new RentPoint(null, "Main", PointType.CENTER, point, new ArrayList<>());

        this.v1 = new Vehicle(null, "Cool scooter", false,
                false, 2.3, 2.9, new HashSet<>(), VehicleType.SCOOTER, rentPoint);
        this.v2 = new Vehicle(null, "Child scooter", false,
                true, 3.4, 1.9, new HashSet<>(), VehicleType.SCOOTER, rentPoint);
        this.v3 = new Vehicle(null, "Bike ", true,
                false, 4.5, 1.0, new HashSet<>(), VehicleType.BIKE, rentPoint);
        rentPointDao.save(rentPoint);
        v1.getBookedDates().add(LocalDate.of(2030, 10, 10));
        v1.getBookedDates().add(LocalDate.of(2040, 12, 12));
        vehicleDao.save(v1);
        vehicleDao.save(v2);
        vehicleDao.save(v3);
    }


    @Test
    @Transactional
    @Rollback
    void findAllFreeAndExpectConsistency() {
        List<Vehicle> retrieved = vehicleDao.findAllFreeFromPoint(rentPointDao.findAll().stream().findFirst().get().getId(),
                LocalDate.of(2030, 10, 10));
        Assertions.assertEquals(2, retrieved.size());

    }

    @Test
    @Transactional
    @Rollback
    void findAllChildishAndExpectConsistency() {

        List<Vehicle> retrieved = vehicleDao.findAllChildish();
        Assertions.assertEquals(1, retrieved.size());

    }

    @Test
    @Transactional
    @Rollback
    void findAllAndExpectConsistency() {

        List<Vehicle> retrieved = vehicleDao.findAllFromPoint(rentPointDao.findAll().stream().findFirst().get().getId());
        Assertions.assertEquals(3, retrieved.size());


    }

    @Test
    @Transactional
    @Rollback
    void getBookedDateAndExpectNoexceptions() {

        Set<LocalDate> retrieved = vehicleDao.getBookedDates(1l);
        Assertions.assertEquals(2, retrieved.size());


    }
}
