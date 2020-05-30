package service;

import repository.OrderRepository;
import entities.Master;
import entities.Order;
import entities.Place;

import java.time.LocalDate;

public class OrderService {
    OrderRepository repository = new OrderRepository();


    public void addOrder(Master master, Place place, LocalDate date) {
        for (int i = 0; i < repository.findAll().length; i++) {
            if (repository.findAll()[ i ] != null && repository.findAll()[ i ].getId() == repository.findAll().length - 1) {
                clearOrders();
            }
            if (repository.findAll()[ i ] == null) {
                Order order = new Order( date, master, place );
                order.setId( i );
                repository.findAll()[ i ] = order;
                repository.save( order );
                break;
            }
        }

    }

    public void removeOrder(long id) {
        for (int i = 0; i < repository.findAll().length; i++) {
            if (repository.findAll()[ i ] != null && repository.findAll()[ i ].getId() == id) {
                repository.findAll()[ i ] = null;
                repository.delete( i );
            }
        }
    }

    public Order findOrderById(long id) {
        for (Order order : repository.findAll()) {
            if (repository.findAll() != null && order.getId() == id) {
                return order;
            }
            throw new IllegalArgumentException( "No such Order in order list!" );
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
        for (Order order : repository.findAll()) {
            if (order != null && order.getId() == id  )     {
                order.setDone();
            repository.save( order );
            }
        }

    }
    public Order[] getOrders() {
        return repository.findAll();
    }

    private void clearOrders() {
        for (int i = 0; i < repository.findAll().length; i++) {
            repository.delete( i );
            repository.findAll()[ i ] = null;
        }
    }
}
