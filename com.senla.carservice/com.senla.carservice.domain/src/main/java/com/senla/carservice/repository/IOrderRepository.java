package com.senla.carservice.repository;


import com.senla.carservice.entity.order.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderRepository extends IGenericRepository {
    Order getById(UUID id);

    List <Order> findAll();

    void delete(UUID id);

    void save(Order order);


}
