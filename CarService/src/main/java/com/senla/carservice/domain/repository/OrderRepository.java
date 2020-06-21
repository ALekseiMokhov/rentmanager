package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepository implements IOrderRepository {
    private List <Order> orders = new ArrayList <>();

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
