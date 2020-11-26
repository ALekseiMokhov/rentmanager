package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.dao.jpa.VehicleDaoJpaImpl;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapperImpl;
import ru.rambler.alexeimohov.entities.Vehicle;
import static org.mockito.BDDMockito.*;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TestVehicleService {

    private VehicleDao vehicleDao = Mockito.mock( VehicleDaoJpaImpl.class );

    private VehicleMapper vehicleMapper = Mockito.mock( VehicleMapperImpl.class ) ;

    private Vehicle vehicle ;

    private LocalDate date;

    private List <Vehicle> vehicleList;

    @BeforeEach
    void init(){
        this.vehicle = new Vehicle();
        this.date = LocalDate.now();
    }

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void setDateForBookingAndExpectVehicleBooked() {
        //given
        given( vehicleDao.findById( anyLong() ) ) .willReturn( vehicle );
        //when
       vehicleService.setDateForBooking( 1l,date );
        //then
        Assertions.assertEquals(true, vehicle.getBookedDates().contains( date ) );
    }


}
