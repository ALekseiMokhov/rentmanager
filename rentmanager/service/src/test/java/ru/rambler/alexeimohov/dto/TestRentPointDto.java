package ru.rambler.alexeimohov.dto;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.entities.enums.PointType;

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
    void init(){
      rentPoint = new RentPoint();
      rentPoint.setId( 856l );
      rentPoint.setPointName( "Main point" );
      rentPoint.setType( PointType.SECOND_LINE );
      rentPoint.setCoordinate( new GeometryFactory().createPoint( new Coordinate(334,58) ) );

      rentPointDto = new RentPointDto();
      rentPointDto.setId("576474");
      rentPointDto.setPointName( "Susan McCassey" );
      rentPointDto.setCoordinate( "POINT (222 111)" );
    }

    @Test
    void MapPointTODtoAndExpectCorrectFieldValues(){
      RentPointDto dtoMapped = rentPointMapper.toDto( rentPoint );
        Assertions.assertEquals("Main point" , dtoMapped.getPointName() );
        Assertions.assertEquals("POINT (334 58)" , dtoMapped.getCoordinate() );
    }

    @Test
    void MapDtoToPointAndExpectCorrectFieldValues(){
       RentPoint rentPointMapped = rentPointMapper.fromDto( rentPointDto ) ;
        Assertions.assertEquals("Susan McCassey" , rentPointMapped.getPointName() );
        Assertions.assertEquals(222, rentPointMapped.getCoordinate().getX() );
    }
}
