package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.domain.repository.IOrderRepository;
import com.senla.carservice.domain.repository.OrderRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final IMasterService masterService;
    private final IPlaceService placeService;
    private static OrderService INSTANCE;

    private OrderService() {
        this.orderRepository = new OrderRepository();
        this.masterService = MasterService.getINSTANCE();
        this.placeService = PlaceService.getINSTANCE();
    }

    public static OrderService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new OrderService();
        }
        return INSTANCE;
    }

    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        List <IMaster> availableMasters = new ArrayList <>();
        for (Speciality speciality : required) {
            IMaster master = masterService.getFreeBySpeciality( startOfExecution, speciality );
            availableMasters.add( master );
            masterService.setMasterForDate( master, startOfExecution );

        }
        Place place = this.placeService.getFreePlace( startOfExecution );
        this.placeService.setPlaceForDate( place, startOfExecution );
        this.placeService.savePlace( place );
        Order order = new Order( date, startOfExecution, availableMasters, place );
        order.setStatus( OrderStatus.MANAGED );
        this.orderRepository.save( order );

    }

    public Order findOrderById(UUID id) {
        return this.orderRepository.findById( id );

    }


    public void shiftOrderExecutionDate(Order order, LocalDate newDate) {

        LocalDate oldDate = order.getStartOfExecution();
        Place oldPlace = order.getPlace();
        Place newPlace = this.placeService.getFreePlace( newDate );
        List <IMaster> oldMasters = order.getMasters();
        Set <Speciality> required = oldMasters.stream()
                .map( m -> m.getSpeciality() )
                .collect( Collectors.toSet() );
        List <IMaster> newMasters = new ArrayList <>();

        this.placeService.setPlaceFree( oldPlace, oldDate );
        this.placeService.savePlace( oldPlace );
        newPlace.getCalendar().setDateForBooking( newDate );
        this.placeService.savePlace( newPlace );

        oldMasters.stream()
                .forEach( master -> master.getCalendar()
                        .deleteBookedDate( oldDate ) );

        for (Speciality speciality : required) {
            IMaster master = masterService.getFreeBySpeciality( newDate, speciality );
            newMasters.add( master );
            masterService.setMasterForDate( master, newDate );
            masterService.saveMaster( master );
        }
        order.setPlace( newPlace );
        order.setMasters( newMasters );
        order.setStartOfExecution( newDate );
        this.orderRepository.save( order );

    }

    public void setNewMasters(Order order) {
        List <IMaster> newMasters = new ArrayList <>();

        for (IMaster master : order.getMasters()) {
            IMaster master1 = this.masterService
                    .getFreeBySpeciality( order.getStartOfExecution(), master.getSpeciality() );
            this.masterService.setMasterForDate( master1, order.getStartOfExecution() );
            this.masterService.saveMaster( master1 );
            newMasters.add( master1 );

            this.masterService.setBookedDateFree( master, order.getStartOfExecution() );
            this.masterService.saveMaster( master );
        }
        order.setMasters( newMasters );
        this.orderRepository.save( order );

    }

    public void cancelOrder(UUID id) {
        for (Order order : this.orderRepository.findAll()) {
            if (order.getId().equals( id )) {
                order.setStatus( OrderStatus.CANCELLED );
                order.getMasters()
                        .stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( order.getStartOfExecution() ) );
                order.getMasters()
                        .stream()
                        .forEach( master -> this.masterService.saveMaster( master ) );

                Place place = order.getPlace();
                this.placeService.setPlaceFree( place, order.getStartOfExecution() );
                this.placeService.savePlace( place );

                this.orderRepository.save( order );
            }
        }

    }

    public void completeOrder(UUID id) {
        for (Order order : this.orderRepository.findAll()) {
            if (order.getId().equals( id )) {
                order.getPlace().getCalendar().deleteBookedDate( LocalDate.now() );
                this.placeService.savePlace( order.getPlace() );

                order.getMasters()
                        .stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( LocalDate.now() ) );
                order.getMasters()
                        .stream()
                        .forEach( master -> this.masterService.saveMaster( master ) );

                order.setStatus( OrderStatus.COMPLETED );
                order.setFinishOfExecution( LocalDate.now() );
                this.orderRepository.save( order );
            }
        }

    }

    public List <Order> getOrders() {
        return this.orderRepository.findAll();
    }

    public List <Order> getOrdersByPrice(OrderStatus status) {
        Comparator <Order> priceComparator = Comparator.comparing( o -> o.getTotalPrice() );
        List <Order> sortedList = this.orderRepository.findAll();
        Collections.sort( sortedList, priceComparator );
        return sortedList
                .stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersByBookedDate(OrderStatus status) {
        Comparator <Order> dateOfBookingComparator = Comparator.comparing( o -> o.getDateBooked() );
        List <Order> sortedList = this.orderRepository.findAll();
        Collections.sort( sortedList, dateOfBookingComparator );
        return sortedList
                .stream()
                .filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersByExecutionDate(OrderStatus status) {
        Comparator <Order> dateOfExecutionComparator = Comparator.comparing( o -> o.getStartOfExecution() );
        List <Order> sortedList = this.orderRepository.findAll();
        Collections.sort( sortedList, dateOfExecutionComparator );
        return sortedList
                .stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersForPeriod(LocalDate start, LocalDate end) {
        return this.orderRepository
                .findAll()
                .stream()
                .filter( o -> o.getStartOfExecution()
                        .compareTo( start ) >= 0 && o.getFinishOfExecution().compareTo( end ) <= 0 )
                .collect( Collectors.toList() );
    }

}
