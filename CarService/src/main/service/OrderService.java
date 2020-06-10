package main.service;

import main.entities.garage.Place;
import main.entities.master.Master;
import main.entities.master.Speciality;
import main.entities.order.Order;
import main.entities.order.OrderStatus;
import main.repository.OrderRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderRepository orderRepository;
    private final MasterService masterService;
    private final PlaceService placeService;

    public OrderService(OrderRepository orderRepository, MasterService masterService, PlaceService placeService) {
        this.orderRepository = orderRepository;
        this.masterService = masterService;
        this.placeService = placeService;
    }


    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        List <Master> availableMasters = new ArrayList <>();
        for (Speciality speciality : required) {
            Master master = masterService.getFreeBySpeciality( startOfExecution, speciality );
            availableMasters.add( master );
            masterService.setMasterForDate( master, startOfExecution );
            masterService.saveMaster( master );
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
        List <Master> oldMasters = order.getMasters();
        Set <Speciality> required = oldMasters.stream()
                .map( m -> m.getSpeciality() )
                .collect( Collectors.toSet() );
        List <Master> newMasters = new ArrayList <>();

        this.placeService.setPlaceFree( oldPlace, oldDate );
        this.placeService.savePlace( oldPlace );
        newPlace.getCalendar().setDateForBooking( newDate );
        this.placeService.savePlace( newPlace );

        oldMasters.stream()
                .forEach( master -> master.getCalendar()
                        .deleteBookedDate( oldDate ) );

        for (Speciality speciality : required) {
            Master master = masterService.getFreeBySpeciality( newDate, speciality );
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
        List <Master> newMasters = new ArrayList <>();
        for (Master master : order.getMasters()) {
            Master master1 = this.masterService
                    .getFreeBySpeciality( order.getStartOfExecution(), master.getSpeciality() );
            newMasters.add( master1 );
            this.masterService.setMasterForDate( master1,order.getStartOfExecution() );
            this.masterService.saveMaster( master1 );
            this.masterService.setBookedDateFree( master, order.getStartOfExecution() );
            this.masterService.saveMaster( master );
        }
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
