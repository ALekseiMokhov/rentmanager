package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.mappers.DateMapper;
import ru.rambler.alexeimohov.entities.Order;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface OrderMapper {
    @Mapping(source = "vehicle.modelName", target = "vehicleName")
    @Mapping(source = "user.fullName", target = "userName")
    OrderDto toDto(Order order);

    @InheritInverseConfiguration
    Order fromDto(OrderDto dto);

    List <Order> listFromDto(List <OrderDto> list);

    List <OrderDto> listToDto(List <Order> list);
}
