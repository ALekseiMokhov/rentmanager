package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.AddressMapper;
import ru.rambler.alexeimohov.entities.Address;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestAddressDto {
    @Autowired
    private AddressMapper addressMapper;

    private Address address;

    private AddressDto addressDto;


    @BeforeEach
    void init() {
        this.address = TestEntitiesFactory.getAddress();
        this.addressDto = TestEntitiesFactory.getAddressDto();
    }

    @Test
    void convertEntityToDtoAndExpectConsistency() {
        AddressDto converted = addressMapper.toDto( address );
        Assertions.assertEquals( "Moscow", converted.getCity() );
        Assertions.assertEquals( "232", converted.getBuildingNumber() );
    }

    @Test
    void convertDtoToEntityAndExpectConsistency() {
        Address converted = addressMapper.fromDto( addressDto );
        Assertions.assertEquals( 693l, converted.getId() );
        Assertions.assertEquals( 1, converted.getBuildingNumber() );
    }
}
