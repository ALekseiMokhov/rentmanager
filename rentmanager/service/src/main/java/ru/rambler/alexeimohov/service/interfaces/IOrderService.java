package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    OrderDto getById(Long id);

    void saveOrUpdate(OrderDto dto);

    void remove(Long id)   ;

    List <OrderDto> getAll();


}
