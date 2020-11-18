package ru.rambler.alexeimohov.service;

import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class AddressService implements IAddressService {
          /*TODO add id to all entities*/
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
    public void saveOrUpdate(AddressDto dto) {
        Address address = mapper.fromDto(dto);
    }

    public void remove (Long id){
        addressDao.remove( id );
    }
    public List <AddressDto> getAddressesByCity(String cityName) {
        return addressDao.findAll().stream()
                .filter( a -> a.getCity().equals( cityName ) )
                .map( a -> mapper.toDto( a ) )
                .collect( Collectors.toList() );
    }

}
