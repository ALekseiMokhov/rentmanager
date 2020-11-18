package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.entities.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);

    Address fromDto(AddressDto dto);

    List <Address> listFromDto(List <AddressDto> list);

    List <AddressDto> listToDto(List <Address> list);
}
