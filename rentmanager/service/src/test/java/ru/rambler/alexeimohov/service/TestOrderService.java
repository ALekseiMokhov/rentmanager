package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.dao.jpa.OrderJpaDaoImpl;
import ru.rambler.alexeimohov.dao.jpa.UserJpaDaoImpl;
import ru.rambler.alexeimohov.dao.jpa.VehicleJpaDaoImpl;
import ru.rambler.alexeimohov.dto.mappers.interfaces.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class TestOrderService {
    private OrderDao orderDao  = Mockito.mock( OrderJpaDaoImpl.class);

    private UserDao userDao = Mockito.mock( UserJpaDaoImpl.class );

    private VehicleDao vehicleDao = Mockito.mock( VehicleJpaDaoImpl.class );

    private VehicleService vehicleService;

    private OrderMapper orderMapper;
    @BeforeEach
    void init() {


    }
}
