package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaRepository extends GenericJpaRepository{
    public OrderJpaRepository() {
        super.setClass( Order.class );
    }
}
