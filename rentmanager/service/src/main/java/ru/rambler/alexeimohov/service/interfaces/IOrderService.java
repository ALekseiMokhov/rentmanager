package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.OrderDto;

import java.util.List;

@Service
public interface IOrderService {
    OrderDto getById(Long id);

    void saveOrUpdate(OrderDto dto);

    void remove(Long id)   ;

    void finish(Long id) ;

    void cancel(Long id);

    List <OrderDto> getAll();


}
