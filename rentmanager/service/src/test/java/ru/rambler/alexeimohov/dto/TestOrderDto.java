package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.OrderMapper;
import ru.rambler.alexeimohov.entities.Order;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestOrderDto {
    @Autowired
    private OrderMapper orderMapper;

    private Order order;

    private  OrderDto orderDto;
    @BeforeEach
    void init(){
    order = TestEntitiesFactory.getOrder();
    orderDto = TestEntitiesFactory.getOrderDto();
    }
    @Test
    void convertEntityToDtoAndExpectConsistency(){
      OrderDto converted = orderMapper.toDto( order );
        Assertions.assertEquals("S1", converted.getVehicleName()  );
        Assertions.assertEquals("Sergei", converted.getUserName()  );
    }

    @Test
    void convertDtoToEntityAndExpectConsistency(){
       Order converted = orderMapper.fromDto( orderDto );
       Assertions.assertEquals( "Alexander",converted.getUser().getFullName() );
    }
}