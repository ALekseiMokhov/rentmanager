package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.Address;

import java.util.List;

@Mapper(componentModel = "spring", uses = GeometryConverter.class)
public interface AddressMapper {
    
    AddressDto toDto(Address address);

    Address fromDto(AddressDto dto);

    List <Address> listFromDto(List <AddressDto> list);

    List <AddressDto> listToDto(List <Address> list);
}
