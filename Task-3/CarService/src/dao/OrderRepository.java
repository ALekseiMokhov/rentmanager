package dao;

import entities.Master;
import entities.Order;

public class OrderRepository implements Dao{
    private Order[] orders = new Order[ 100 ];

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public Object[] findAll() {
        return orders;
    }

    @Override
    public void delete(int id) {
       orders[ (int) id ] = null;
    }

    @Override
    public void save(Object o) {
        if(o instanceof Order ==false)   {
            throw new IllegalArgumentException( "Wrong object type!" )  ;
        }
        if(orders[((Order) o).getId()]==null) {
            orders[((Order) o).getId()] = (Order) o;
        }
    }


}
