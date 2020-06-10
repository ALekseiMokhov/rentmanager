package main;

import main.entities.garage.Place;
import main.entities.master.*;
import main.entities.order.Order;
import main.repository.MasterRepository;
import main.repository.OrderRepository;
import main.repository.PlaceRepository;
import main.service.MasterService;
import main.service.OrderService;
import main.service.PlaceService;
import main.util.Calendar;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Domain {

    public static void main(String[] args) {
        PlaceService placeService = new PlaceService( new PlaceRepository() );
        MasterService masterService = new MasterService( new MasterRepository() );
        OrderService orderService = new OrderService( new OrderRepository(), masterService, placeService );

        for (int i = 0; i < 10; i++) {
            placeService.savePlace( new Place( new Calendar() ) );
        }

        List <Master> masters = Arrays.asList
                ( new Reshaper( "Andrew", 2.3, new Calendar(), Speciality.RESHAPER ),
                        new Painter( "John", 4.4, new Calendar(), Speciality.PAINTER ),
                        new Electrician( "Fred", 1.4, new Calendar(), Speciality.ELECTRICIAN ),
                        new Mechanic( "Joe", 4.6, new Calendar(), Speciality.MECHANIC ),
                        new Reshaper( "AAA", 1.1, new Calendar(), Speciality.RESHAPER ),
                        new Painter( "BBB", 2.2, new Calendar(), Speciality.PAINTER ),
                        new Electrician( "CCC", 3.3, new Calendar(), Speciality.ELECTRICIAN ),
                        new Mechanic( "DDD", 4.4, new Calendar(), Speciality.MECHANIC ) );
        for (Master master : masters) {
            masterService.saveMaster( master );
        }


        for (int i = 1; i <= 10; i++) {
            orderService.addOrder( LocalDate.now(), LocalDate.of( 2020, 6, i ), Set.of( Speciality.MECHANIC ) );
        }
        for (int i = 11; i <= 20; i++) {
            orderService.addOrder( LocalDate.now(), LocalDate.of( 2020, 6, i ), Set.of( Speciality.RESHAPER ) );
        }
        for (int i = 21; i <= 30; i++) {
            orderService.addOrder( LocalDate.now(), LocalDate.of( 2020, 6, i ), Set.of( Speciality.PAINTER ) );
        }

        for (Order order : orderService.getOrders()) {
            printOrder( order );
        }
    }

    public static void printOrder(Order order) {
        System.out.println( order );
    }

}
