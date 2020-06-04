package main.service;

import main.entities.garage.Place;
import main.entities.master.Master;
import main.entities.order.Order;
import main.entities.order.OrderStatus;
import main.repository.OrderRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderRepository repository = new OrderRepository();
    private final MasterService masterService = new MasterService();
    private final PlaceService placeService = new PlaceService();

    public void addOrder(LocalDate date, LocalDate startOfExecution, List <Master> masters, Place place) {
        Order order = new Order( date, startOfExecution, masters, place );
        order.setStatus( OrderStatus.MANAGED );
        this.repository.save( order );
        for (Master master : masters) {
            master.bookMaster( startOfExecution );
            this.masterService.saveMaster( master );
        }
        place.bookPlace( startOfExecution );
        this.placeService.savePlace( place );

    }

    public Order findOrderById(UUID id) {
        return this.repository.findById( id );

    }


    public void shiftOrderExecutionDate(Order order, LocalDate newDate) {
        LocalDate oldDate = order.getStartOfExecution();

        order.getPlace().unBookPlace( oldDate );
        order.getPlace().bookPlace( newDate );
        this.placeService.savePlace( order.getPlace() );

        order.getMasters().stream().forEach( master -> master.unBookMaster( oldDate ) );
        order.getMasters().stream().forEach( master -> master.bookMaster( newDate ) );
        order.getMasters().stream().forEach( master -> masterService.saveMaster( master ) );

        order.setStartOfExecution( newDate );
        this.repository.save( order );

    }

    public void setNewMaster(Order order, Master old, Master current) {
        if (!current.isFreeForDate( order.getStartOfExecution() )) {
            throw new IllegalArgumentException( "Master is booked for the date!" );
        }
        old.unBookMaster( order.getStartOfExecution() );
        current.bookMaster( order.getStartOfExecution() );
        this.masterService.saveMaster( old );
        this.masterService.saveMaster( current );
        order.setMaster( old, current );
        this.repository.save( order );

    }

    public void cancelOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order != null && order.getId().equals( id )) {
                order.setStatus( OrderStatus.CANCELLED );
                order.getMasters().stream().forEach( master -> master.unBookMaster( order.getStartOfExecution() ) );
                order.getMasters().stream().forEach( master -> this.masterService.saveMaster( master ) );

                Place place = order.getPlace();
                place.unBookPlace( order.getStartOfExecution() );
                this.placeService.savePlace( place );

                this.repository.save( order );
            }
        }

    }

    public void completeOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order.getId().equals( id )) {
                order.getPlace().unBookPlace( LocalDate.now() );
                this.placeService.savePlace( order.getPlace() );

                order.getMasters().stream().forEach( master -> master.unBookMaster( LocalDate.now() ) );
                order.getMasters().stream().forEach( master -> this.masterService.saveMaster( master ) );

                order.setStatus( OrderStatus.COMPLETED );
                order.setFinishOfExecution( LocalDate.now() );
                this.repository.save( order );
            }
        }

    }

    public List <Order> getOrders() {
        return this.repository.findAll();
    }

    public List <Order> getOrdersByPrice(OrderStatus status) {
        Comparator <Order> priceComparator = Comparator.comparing( o -> o.getTotalPrice() );
        Collections.sort( this.repository.findAll(), priceComparator );
        return this.repository.findAll().stream().filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersByBookedDate(OrderStatus status) {
        Comparator <Order> dateOfBookingComparator = Comparator.comparing( o -> o.getDateBooked() );
        Collections.sort( this.repository.findAll(), dateOfBookingComparator );
        return this.repository.findAll().stream().filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersByExecutionDate(OrderStatus status) {
        Comparator <Order> dateOfExecutionComparator = Comparator.comparing( o -> o.getStartOfExecution() );
        Collections.sort( this.repository.findAll(), dateOfExecutionComparator );
        return this.repository.findAll().stream().filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersForPeriod(LocalDate start, LocalDate end) {
        return this.repository
                .findAll()
                .stream()
                .filter( o -> o.getStartOfExecution()
                        .compareTo( start ) >= 0 && o.getFinishOfExecution().compareTo( end ) <= 0 )
                .collect( Collectors.toList() );
    }

}
