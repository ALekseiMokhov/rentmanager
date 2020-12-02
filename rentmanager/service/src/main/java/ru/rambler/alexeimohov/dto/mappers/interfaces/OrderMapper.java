package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.*;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.Order;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = GeometryConverter.class)
public interface OrderMapper {
    @AfterMapping
    default void afterMapping(@MappingTarget Order target, OrderDto source) {
        if (source.getCreationTime() == null) {
            target.setCreationTime( LocalDateTime.now() );
        }

    }

    @Mapping(source = "hasValidSubscription", target = "hasValidSubscription")
    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "vehicle", target = "vehicleDto")
    OrderDto toDto(Order order);


    @InheritInverseConfiguration
    Order fromDto(OrderDto dto);

    List <Order> listFromDto(List <OrderDto> list);

    List <OrderDto> listToDto(List <Order> list);
}
