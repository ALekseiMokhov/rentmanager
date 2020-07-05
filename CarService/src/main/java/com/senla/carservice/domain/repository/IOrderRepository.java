package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.order.Order;
import dependency.injection.annotations.components.Component;

import java.util.List;
import java.util.UUID;
@Component
public interface IOrderRepository {
    Order findById(UUID id);

    List <Order> findAll();

    void delete(UUID id);

    void save(Order order);
}
