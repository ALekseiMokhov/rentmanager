package com.senla.carservice.service;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.interfaces.IOrderRepository;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.service.interfaces.IOrderService;
import com.senla.carservice.service.interfaces.IPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j

@Transactional
    public class OrderService implements IOrderService {
    @Autowired
    @Qualifier("orderJpaRepository")
    private IGenericRepository <Order> repository;
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;
    @Autowired
    @Qualifier("placeService")
    private IPlaceService placeService;


    public OrderService() {
    }

    public OrderService(IOrderRepository orderRepository, IMasterService masterService, IPlaceService placeService) {
        this.repository = orderRepository;
        this.masterService = masterService;
        this.placeService = placeService;
    }


    @Transactional
    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        List <AbstractMaster> availableMasters = new ArrayList <>();
        for (Speciality speciality : required) {
            AbstractMaster master = masterService.getFreeBySpeciality( startOfExecution, speciality );
            availableMasters.add( master );
            masterService.setMasterForDate( master.getId(), startOfExecution );

        }
        Place place = this.placeService.getFreePlace( startOfExecution );
        this.placeService.setPlaceForDate( place.getId(), startOfExecution );
        Order order = new Order( date, startOfExecution, place, availableMasters );
        order.setStatus( OrderStatus.MANAGED );

        this.repository.save( order );


    }

    @Override
    public void saveOrder(Order order) {

        this.repository.save( order );

    }

    public Order findOrderById(UUID id) {

        return this.repository.getById( id );

    }

    @Override
    public void deleteOrder(UUID id) {

        this.repository.delete( id );

    }

    public void shiftOrderExecutionDate(UUID id, LocalDate newDate) {
        Order order = this.repository.getById( id );
        LocalDate oldDate = order.getStartOfExecution();

        Place oldPlace = order.getPlace();
        Place newPlace = this.placeService.getFreePlace( newDate );

        List <AbstractMaster> oldMasters = order.getMasters();
        Set <Speciality> required = oldMasters.stream()
                .map( m -> m.getSpeciality() )
                .collect( Collectors.toSet() );
        List <AbstractMaster> newMasters = new ArrayList <>();

        this.placeService.setPlaceFree( oldPlace.getId(), oldDate );

        newPlace.getCalendar().setDateForBooking( newDate );


        oldMasters.stream()
                .forEach( master -> master.getCalendar()
                        .deleteBookedDate( oldDate ) );

        for (Speciality speciality : required) {
            AbstractMaster master = masterService.getFreeBySpeciality( newDate, speciality );
            newMasters.add( master );
            masterService.setMasterForDate( master.getId(), newDate );

        }
        order.setPlace( newPlace );
        order.setMasters( newMasters );
        order.setStartOfExecution( newDate );

    }

    public void setNewMasters(UUID id) {
        Order order = this.repository.getById( id );
        List <AbstractMaster> newMasters = new ArrayList <>();

        for (AbstractMaster master : order.getMasters()) {
            AbstractMaster master1 = this.masterService
                    .getFreeBySpeciality( order.getStartOfExecution(), master.getSpeciality() );
            this.masterService.setMasterForDate( master1.getId(), order.getStartOfExecution() );
            newMasters.add( master1 );

            this.masterService.setBookedDateFree( master.getId(), order.getStartOfExecution() );
        }
        order.setMasters( newMasters );

    }

    public void cancelOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order.getId().equals( id )) {
                order.setStatus( OrderStatus.CANCELLED );
                order.getMasters().stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( order.getStartOfExecution() ) );
                order.getMasters()
                        .stream()
                        .forEach( master -> this.masterService.saveMaster( master ) );

                Place place = order.getPlace();
                this.placeService.setPlaceFree( place.getId(), order.getStartOfExecution() );
                this.placeService.savePlace( place );


            }
        }

    }

    @Transactional
    public void completeOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order.getId().equals( id )) {
                order.getPlace().getCalendar().deleteBookedDate( LocalDate.now() );

                order.getMasters().stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( LocalDate.now() ) );


                order.setStatus( OrderStatus.COMPLETED );
                order.setFinishOfExecution( LocalDate.now() );

            }
        }

    }

    public List <Order> getOrders() {
        return this.repository.findAll();
    }


    public List <Order> getOrdersByPrice(OrderStatus status) {
        Comparator <Order> priceComparator = Comparator.comparing( o -> o.getTotalPrice() );
        List <Order> sortedList = null;
        Collections.sort( sortedList, priceComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersByBookedDate(OrderStatus status) {
        Comparator <Order> dateOfBookingComparator = Comparator.comparing( o -> o.getDateBooked() );
        List <Order> sortedList = null;

        sortedList = this.repository.findAll();
        Collections.sort( sortedList, dateOfBookingComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersByExecutionDate(OrderStatus status) {
        Comparator <Order> dateOfExecutionComparator = Comparator.comparing( o -> o.getStartOfExecution() );
        List <Order> sortedList = null;

        sortedList = this.repository.findAll();


        Collections.sort( sortedList, dateOfExecutionComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersForPeriod(LocalDate start, LocalDate end) {
        List <Order> res = new ArrayList <>();

        res.addAll( this.repository.findAll() );

        return res.stream()
                .filter( o -> o.getStartOfExecution()
                        .compareTo( start ) >= 0 && o.getFinishOfExecution().compareTo( end ) <= 0 )
                .collect( Collectors.toList() );
    }


}
