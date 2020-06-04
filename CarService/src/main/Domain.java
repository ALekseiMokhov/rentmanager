package main;

import main.entities.garage.Place;
import main.entities.master.Master;
import main.entities.master.Mechanic;
import main.service.OrderService;

import java.time.LocalDate;
import java.util.List;

public class Domain {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Place place = new Place();
        Master Ivan = new Mechanic( "Ivan", 22.3 );

        orderService.addOrder( LocalDate.now(), LocalDate.of( 2020, 06, 30 ), List.of( Ivan ), place );

        orderService.getOrders().stream().forEach( System.out::println );
    }
}
