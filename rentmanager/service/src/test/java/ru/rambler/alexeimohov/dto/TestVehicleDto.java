package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapper;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.VehicleType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestVehicleDto {
    @Autowired
    private VehicleMapper vehicleMapper;

    private Vehicle vehicle;

    private VehicleDto vehicleDto;

    private RentPoint rentPoint;

    @BeforeEach
    void init() {
        vehicle = new Vehicle();
        vehicle.setId( 1l );
        vehicle.setType( VehicleType.SCOOTER );
        vehicle.setChildish( false );
        vehicle.setRentPrice( 13.4 );
        vehicle.setFinePrice( 12.0 );
        vehicle.setModelName( "S1" );
        rentPoint = new RentPoint();
        rentPoint.setId( 2l );
        rentPoint.setPointName( "Main point" );
        vehicle.setRentPoint( rentPoint );
    }

    @Test
    void ConvertToDtoAndExpectConsistentFields() {
        vehicleDto = vehicleMapper.toDto( vehicle );
        Assertions.assertNotNull( vehicleDto.getRentPoint() );
        Assertions.assertEquals( vehicleDto.getModelName(), "S1" );
    }
}
