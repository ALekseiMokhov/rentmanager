package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.AddressDto;

import java.util.List;

@Service
public interface IAddressService {


    void remove(Long id);

    AddressDto getById(Long id);

    void saveOrUpdate(AddressDto dto);

    List <AddressDto> getAddressesByCity(String cityName);

    List <AddressDto> getAll();
}
