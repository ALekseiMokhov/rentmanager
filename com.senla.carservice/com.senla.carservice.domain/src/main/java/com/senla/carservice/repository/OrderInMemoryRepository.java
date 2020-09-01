package com.senla.carservice.repository;


import com.senla.carservice.entity.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Deprecated
public class OrderInMemoryRepository implements IOrderRepository {
    private final List <Order> orders = new ArrayList <>();

    @Override
    public Order findById(UUID id) {
        return this.orders.stream()
                .filter( master -> master.getId().equals( id ) )
                .findFirst()
                .get();
    }

    @Override
    public List <Order> findAll() {
        return this.orders;
    }

    @Override
    public void delete(UUID id) {
        this.orders.removeIf( order -> order.getId().equals( id ) );

    }

    @Override
    public void save(Order order) {
        if (!this.orders.contains( order )) {
            this.orders.add( order );
        } else {
            this.orders.set( this.orders.indexOf( order ), order );
        }

    }


}
