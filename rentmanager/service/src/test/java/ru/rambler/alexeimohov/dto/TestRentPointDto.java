package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.entities.RentPoint;

/*
 * Integration test for DTO using local package Configuration*/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestRentPointDto {

    @Autowired
    private RentPointMapper rentPointMapper;

    private RentPoint rentPoint;

    private RentPointDto rentPointDto;

    @BeforeEach
    void init() {
        this.rentPoint = TestEntitiesFactory.getRentPoint();

        this.rentPointDto = TestEntitiesFactory.getRentPointDto();
    }

    @Test
    void MapPointToDtoAndExpectCorrectFieldValues() {
        RentPointDto dtoMapped = rentPointMapper.toDto( rentPoint );
        System.out.println( dtoMapped.getCoordinate() );
        Assertions.assertEquals( "Main point", dtoMapped.getPointName() );
        Assertions.assertEquals( "POINT (334 58)", dtoMapped.getCoordinate() );
    }

    @Test
    void MapDtoToPointAndExpectCorrectFieldValues() {
        RentPoint rentPointMapped = rentPointMapper.fromDto( rentPointDto );
        Assertions.assertEquals( "Susan McCassey", rentPointMapped.getPointName() );
        Assertions.assertEquals( 222, rentPointMapped.getCoordinate().getX() );
    }
}
