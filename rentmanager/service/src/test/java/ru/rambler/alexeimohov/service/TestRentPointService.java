package ru.rambler.alexeimohov.service;

import com.vividsolutions.jts.geom.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.RentPointDao;
import ru.rambler.alexeimohov.dao.jpa.RentPointDaoJpaImpl;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapperImpl;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TestRentPointService {

    private RentPointDao rentPointDao = Mockito.mock( RentPointDaoJpaImpl.class );

    private RentPointMapper rentPointMapper = Mockito.mock( RentPointMapperImpl.class );

    @InjectMocks
    private RentPointService rentPointService;

    @Test
    void getByCoordinateShouldTriggerDao() {
        //when
        rentPointService.getByCoordinate( 2.34, 4782.0 );
        //then
        verify( rentPointDao, times( 1 ) ).getByCoordinate( any( Point.class ) );
    }
}
