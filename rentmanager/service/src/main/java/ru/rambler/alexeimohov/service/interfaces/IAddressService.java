package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.AddressDto;

import java.util.List;

@Service
public interface IAddressService {
    List <AddressDto>getAddressesByCity(String cityName);
    void remove(Long id);
    AddressDto getById(Long id);
    void saveOrUpdate(AddressDto dto);
}
