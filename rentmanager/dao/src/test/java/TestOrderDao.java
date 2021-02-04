import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.Privilege;
import ru.rambler.alexeimohov.entities.enums.Role;
import ru.rambler.alexeimohov.entities.enums.VehicleType;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class TestOrderDao {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private VehicleDao vehicleDao;

    private User user;
    private Order order;
    private Vehicle vehicle;

    @BeforeEach
    @Transactional
    void init() {
        this.user = new User();
        user.setUsername("Sergey Borisov");
        user.setEmail("spring_coder@gmail.com");
        user.setPassword("7jfw56hjj8qlb");
        user.setPhoneNumber(8_999_444_00_00l);
        user.setRole(Role.ROLE_ADMIN);
        user.setPrivilege(Privilege.PARTNER);

        this.vehicle = new Vehicle();
        vehicle.setType(VehicleType.SCOOTER);
        vehicle.setFinePrice(30.2);
        vehicle.setRentPrice(5.3);

        this.order = new Order();
        order.setBlockedFunds(10.6);
        order.setHasValidSubscription(false);
        order.setVehicle(vehicle);
        order.setUser(user);

    }

    @Test
    @Transactional
    @Rollback
    void persistOrderAndExpectConsistency() {
        vehicleDao.save(vehicle);
        userDao.save(user);
        orderDao.save(order);
        Assertions.assertEquals(1, vehicleDao.findAll().size());
        Assertions.assertEquals(order.getUser(), userDao.findByUserName("Sergey Borisov"));
        Assertions.assertEquals(order.getVehicle(), vehicleDao.findAll().get(0));

    }

}
