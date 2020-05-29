package service;

import dao.MasterRepository;
import dao.OrderRepository;
import entities.Master;
import entities.Order;
import entities.Place;

import java.time.LocalDate;

public class OrderService {
    OrderRepository repository = new OrderRepository();
    MasterRepository masterRepository = new MasterRepository();

    private Order[] orders = (Order[]) repository.findAll();
    private Master[] masters = (Master[]) masterRepository.findAll();


    public void addOrder(Master master, Place place, LocalDate date) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[ i ] != null && orders[ i ].getId() == orders.length - 1) {
                clearOrders();
            }
            if (orders[ i ] == null) {
                Order order = new Order( date, master, place );
                order.setId( i );
                orders[ i ] = order;
                repository.save( order );
                break;
            }
        }

    }

    public void removeOrder(long id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[ i ] != null && orders[ i ].getId() == id) {
                orders[ i ] = null;
                repository.delete( i );
            }
        }
    }

    public Order findOrderById(long id) {
        for (Order order : orders) {
            if (orders != null && order.getId() == id) {
                return order;
            }
            throw new IllegalArgumentException( "No such entities.Order in order list!" );
        }

        return null;
    }

    public void shiftOrderDate(Order order, LocalDate newDate) {
        order.setDateBooked( newDate );
        repository.save( order );
    }

    public void setNewMaster(Order order, Master master) {
        order.setMaster( master );
        repository.save( order );

    }

    public void closeOrder(int id) {
        for (Order order : orders) {
            if (order != null && order.getId() == id  )     {
                order.setDone();
            repository.save( order );
            }
        }

    }
    public Order[] getOrders() {
        return orders;
    }

    private void clearOrders() {
        for (int i = 0; i < orders.length; i++) {
            repository.delete( i );
            orders[ i ] = null;
        }
    }
}
