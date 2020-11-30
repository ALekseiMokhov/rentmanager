package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapper;
import ru.rambler.alexeimohov.entities.Vehicle;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestVehicleDto {

    @Autowired
    private VehicleMapper vehicleMapper;

    private Vehicle vehicle;

    private VehicleDto vehicleDto;

    @BeforeEach
    void init() {
        this.vehicle = TestEntitiesFactory.getVehicle();
        this.vehicleDto = TestEntitiesFactory.getVehicleDto();
    }

    @Test
    void convertToDtoAndExpectConsistentFields() {
        VehicleDto retrieved = vehicleMapper.toDto( vehicle );
        Assertions.assertNotNull( retrieved.getRentPoint().getCoordinate() );
        Assertions.assertEquals( retrieved.getModelName(), "S1" );
        Assertions.assertEquals(  "true" ,retrieved.getIsChildish());
    }

    @Test
    void convertFromDtoANdExpectConsistentFields() {
        Vehicle retrieved = vehicleMapper.fromDto( vehicleDto );
        Assertions.assertEquals( 3.5, retrieved.getRentPrice() );
        Assertions.assertEquals( true, retrieved.getIsChildish() );
        Assertions.assertEquals( true, retrieved.getIsHumanPowered() );
    }
}
