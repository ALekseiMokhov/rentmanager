package com.senla.carservice.repository;


import com.senla.carservice.entity.order.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Qualifier("orderRepositoryJpa")
public interface IOrderRepository {
    Order findById(UUID id);

    List <Order> findAll();

    void delete(UUID id);

    void save(Order order);


}
