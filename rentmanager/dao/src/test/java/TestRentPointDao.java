import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
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
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.PointType;


@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class TestRentPointDao {
    @Autowired
    RentPointDao rentPointDao;

    RentPoint testingPoint;

    @BeforeEach
    void instantiate() {
        this.testingPoint = new RentPoint();
        testingPoint.setPointName( "Main point" );
        testingPoint.setType( PointType.CENTER );
        testingPoint.addVehicle( new Vehicle() );
        testingPoint.setCoordinate( new GeometryFactory().createPoint( new Coordinate( 2985, 3467 ) ) );

    }

    @Test
    @Transactional
    @Rollback
    void persistAndExpectNoExceptions() {
        rentPointDao.save( testingPoint );
        RentPoint pointCreated = rentPointDao.findById( 1l );
        Assertions.assertNotNull( testingPoint );
        Assertions.assertEquals( testingPoint, pointCreated );
    }
}
