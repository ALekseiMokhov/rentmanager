package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderJpaRepository extends GenericJpaRepository {
    public OrderJpaRepository() {
        super.setClass(Order.class);
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("select distinct o from Order o left join fetch o.masters").getResultList();
    }
}
