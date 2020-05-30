package repository;

import entities.Master;
import entities.Order;

public class OrderRepository implements Repository<Order>{
    private Order[] orders = new Order[ 100 ];

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public Order[] findAll() {
        return orders;
    }

    @Override
    public void delete(int id) {
       orders[ (int) id ] = null;
    }

    @Override
    public void save(Order order) {

        if(orders[order.getId()]==null) {
            orders[order.getId()] = order;
        }
    }


}
