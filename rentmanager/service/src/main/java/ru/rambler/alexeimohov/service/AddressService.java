package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.AddressDao;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.AddressMapper;
import ru.rambler.alexeimohov.entities.Address;
import ru.rambler.alexeimohov.service.interfaces.IAddressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AddressService implements IAddressService {

    private AddressDao addressDao;

    private AddressMapper mapper;
    

    public AddressService(AddressDao addressDao, AddressMapper mapper) {
        this.addressDao = addressDao;
        this.mapper = mapper;
    }

    public AddressDto getById(Long id){
       return mapper.toDto( addressDao.findById( id ) ) ;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(AddressDto dto) {
        Address address = mapper.fromDto(dto);
        if (addressDao.findById( address.getId() ) == null) {
            addressDao.save( address );
            log.debug( "address saved " );
        } else {
            addressDao.update( address );
            log.debug( "address.updated" );
        }
    }
    @Transactional(readOnly = false)
    public void remove (Long id){
        addressDao.remove( id );
        log.info( "card deleted : "+id );
    }

    public List <AddressDto> getAddressesByCity(String cityName) {
        return addressDao.findAll().stream()
                .filter( a -> a.getCity().equals( cityName ) )
                .map( a -> mapper.toDto( a ) )
                .collect( Collectors.toList() );
    }


    @Override
    public List <AddressDto> getAll() {
        return addressDao.findAll().stream()
                .map( a -> mapper.toDto( a ) )
                .collect( Collectors.toList() );
    }
}
