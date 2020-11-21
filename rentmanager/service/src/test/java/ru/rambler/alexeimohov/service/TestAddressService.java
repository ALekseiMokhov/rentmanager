package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.AddressDao;
import ru.rambler.alexeimohov.dao.jpa.AddressDaoJpaImpl;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.AddressMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.AddressMapperImpl;
import ru.rambler.alexeimohov.entities.Address;

import java.util.List;

import static org.mockito.BDDMockito.*;


/*
* Unit tests with BDD mockito and classic mockito methods*/
@ExtendWith(MockitoExtension.class)
class TestAddressService {

    private Address address;

    private AddressDto addressDto;

    private AddressDao addressDao = Mockito.mock( AddressDaoJpaImpl.class ) ;

    private AddressMapper mapper = Mockito.mock( AddressMapperImpl.class ) ;

    @InjectMocks
    private AddressService service;

    @BeforeEach
    void init() {
        this.address = new Address(1l,"Orel","Lenina",34,null );
        this.addressDto = new AddressDto();
    }

    @Test
    void getByIdAndExpectMappingToDto() {
        //given
        when( addressDao.findById( any() ) ).thenReturn( address ) ;
        //when
            service.getById( 1l );
        //then
        verify(mapper, times( 1 )).toDto( any() );
    }

    @Test
    void saveNonExistingEntityAndExpectSaveMethod() {
        //given
        when( mapper.fromDto( any() ) ).thenReturn( address );
        given( addressDao.findById( 1l ) ).willReturn( null );
        //when
        service.saveOrUpdate( addressDto );
        //then
        then(addressDao).should().save( address );
    }


    @Test
    void getAddressesByCityAndExpectCertainResult() {
        //given
        given( addressDao.findAll() ).willReturn( List.of(address,new Address(3l,"Moscow","1",14,null )) ) ;
        //when
            service.getAddressesByCity( "Orel" ) ;
        //then
        verify( mapper ,times( 1 ) ).toDto( any());
    }


}
