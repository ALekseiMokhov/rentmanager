package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.entities.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "vehicle.modelName", target = "vehicleName")
    @Mapping(source = "user.fullName", target = "userName")
    @Mapping(source = "hasValidSubscription", target ="hasValidSubscription" )
    OrderDto toDto(Order order);

 /*   @Mapping(source = "vehicleName", target = "vehicle.modelName")
    @Mapping(source = "userName", target = "user.fullName")*/
    @InheritInverseConfiguration
    Order fromDto(OrderDto dto);

    List <Order> listFromDto(List <OrderDto> list);

    List <OrderDto> listToDto(List <Order> list);
}
