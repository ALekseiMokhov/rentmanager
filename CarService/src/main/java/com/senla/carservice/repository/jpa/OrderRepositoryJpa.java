package com.senla.carservice.repository.jpa;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import dependency.injection.annotations.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
@Qualifier
public class OrderRepositoryJpa implements IOrderRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryJpa.class  )   ;
    @Override
    public Order findById(UUID id) {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public List <Order> findAll() {
        try{

        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        try{

        }
        catch(Exception e){

        }

    }

    @Override
    public void save(Order order) {
        try{

        }
        catch(Exception e){

        }

    }
}
