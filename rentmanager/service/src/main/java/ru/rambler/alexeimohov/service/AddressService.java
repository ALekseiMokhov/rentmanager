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

    public AddressDto getById(Long id) {
        return mapper.toDto(addressDao.findById(id));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(AddressDto dto) {
        Address address = mapper.fromDto(dto);
        try {
            if (address.getId() == null) {
                addressDao.save(address);
            } else {
                addressDao.update(address);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to save address " + address.toString());
        }
    }

    @Transactional(readOnly = false)
    public void remove(Long id) {
        addressDao.remove(id);
        log.info("card deleted : " + id);
    }

    public List<AddressDto> getAddressesByCity(String cityName) {
        return addressDao.findAllSortedByCity(cityName).stream()
                .map(a -> mapper.toDto(a))
                .collect(Collectors.toList());
    }

    public AddressDto getAddressByPointId(long id) {
        return mapper.toDto(addressDao.findById(id));
    }

    @Override
    public List<AddressDto> getAll() {
        return addressDao.findAll().stream()
                .map(a -> mapper.toDto(a))
                .collect(Collectors.toList());
    }
}
