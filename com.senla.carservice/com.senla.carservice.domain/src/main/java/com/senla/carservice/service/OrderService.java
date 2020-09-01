package com.senla.carservice.service;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.IMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.repository.IOrderRepository;
import com.senla.carservice.util.csv.CsvOrderParser;
import com.senla.carservice.util.csv.CsvOrderWriter;
import com.senla.carservice.util.serialisation.GsonOrderParser;
import com.senla.carservice.util.serialisation.GsonOrderWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    @Qualifier("orderRepositoryJpa")
    private IOrderRepository orderRepository;
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;
    @Autowired
    @Qualifier("placeService")
    private IPlaceService placeService;


    public OrderService() {
    }

    public OrderService(IOrderRepository orderRepository, IMasterService masterService, IPlaceService placeService) {
        this.orderRepository = orderRepository;
        this.masterService = masterService;
        this.placeService = placeService;
    }


    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        List <IMaster> availableMasters = new ArrayList <>();
        for (Speciality speciality : required) {
            IMaster master = masterService.getFreeBySpeciality( startOfExecution, speciality );
            availableMasters.add( master );
            masterService.setMasterForDate( master.getId(), startOfExecution );

        }
        Place place = this.placeService.getFreePlace( startOfExecution );
        this.placeService.setPlaceForDate( place.getId(), startOfExecution );
        Order order = new Order( date, startOfExecution, place, availableMasters );
        order.setStatus( OrderStatus.MANAGED );
        try {
            this.orderRepository.save( order );
        } catch (Exception e) {
            log.error("failed to save order! "+e);
            throw new RuntimeException();

        }

    }

    public void addOrder(LocalDate date, LocalDate startOfExecution, List <IMaster> masters, Place place, UUID id) {
        this.placeService.savePlace( place.getId() );
        masters.stream()
                .forEach( m -> this.masterService.saveMaster( m ) );

        try {
            this.orderRepository.save( new Order( id, date, startOfExecution, place, masters ) );
        } catch (Exception e) {
            log.error("failed to save order! " +e);
            throw new RuntimeException();

        }


    }

    @Override
    public void saveOrder(Order order) {

        try {
            this.orderRepository.save( order );
        } catch (Exception e) {
            log.error("failed to save order! " +e);
            throw new RuntimeException();

        }
    }

    public Order findOrderById(UUID id) {
        try {
            return this.orderRepository.findById( id );
        } catch (Exception e) {
            log.error("failed to save order! "+e);
            throw new RuntimeException();

        }
    }

    @Override
    public void deleteOrder(UUID id) {
        try {
            this.orderRepository.delete( id );
        } catch (Exception e) {
            log.error( "failed  to delete order! " +e);
            throw new RuntimeException();

        }
    }

    public void shiftOrderExecutionDate(UUID id, LocalDate newDate) {
        Order order = this.orderRepository.findById( id );
        LocalDate oldDate = order.getStartOfExecution();
        Place oldPlace = order.getPlace();
        Place newPlace = this.placeService.getFreePlace( newDate );
        List <IMaster> oldMasters = order.getMasters();
        Set <Speciality> required = oldMasters.stream()
                .map( m -> m.getSpeciality() )
                .collect( Collectors.toSet() );
        List <IMaster> newMasters = new ArrayList <>();

        this.placeService.setPlaceFree( oldPlace.getId(), oldDate );
        this.placeService.savePlace( oldPlace.getId() );
        newPlace.getCalendar().setDateForBooking( newDate );
        this.placeService.savePlace( newPlace.getId() );

        oldMasters.stream()
                .forEach( master -> master.getCalendar()
                        .deleteBookedDate( oldDate ) );

        for (Speciality speciality : required) {
            IMaster master = masterService.getFreeBySpeciality( newDate, speciality );
            newMasters.add( master );
            masterService.setMasterForDate( master.getId(), newDate );
            masterService.saveMaster( master );
        }
        order.setPlace( newPlace );
        order.setMasters( newMasters );
        order.setStartOfExecution( newDate );
        try {
            this.orderRepository.save( order );
        } catch (Exception e) {
            log.error( "failed to save order!" +e);
            throw new RuntimeException();

        }

    }

    public void setNewMasters(UUID id) {
        Order order = this.orderRepository.findById( id );
        List <IMaster> newMasters = new ArrayList <>();

        for (IMaster master : order.getMasters()) {
            IMaster master1 = this.masterService
                    .getFreeBySpeciality( order.getStartOfExecution(), master.getSpeciality() );
            this.masterService.setMasterForDate( master1.getId(), order.getStartOfExecution() );
            this.masterService.saveMaster( master1 );
            newMasters.add( master1 );

            this.masterService.setBookedDateFree( master.getId(), order.getStartOfExecution() );
            this.masterService.saveMaster( master );
        }
        order.setMasters( newMasters );
        try {
            this.orderRepository.save( order );
        } catch (Exception e) {
            log.error( "failed to save order! " +e);
            throw new RuntimeException();

        }

    }

    public void cancelOrder(UUID id) {
        for (Order order : this.orderRepository.findAll()) {
            if (order.getId().equals( id )) {
                order.setStatus( OrderStatus.CANCELLED );
                order.getMasters().stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( order.getStartOfExecution() ) );
                order.getMasters()
                        .stream()
                        .forEach( master -> this.masterService.saveMaster( master ) );

                Place place = order.getPlace();
                this.placeService.setPlaceFree( place.getId(), order.getStartOfExecution() );
                this.placeService.savePlace( place.getId() );

                try {
                    this.orderRepository.save( order );
                } catch (Exception e) {
                    log.error( "failed to save order! " +e);
                    throw new RuntimeException();


                }
            }
        }

    }

    public void completeOrder(UUID id) {
        for (Order order : this.orderRepository.findAll()) {
            if (order.getId().equals( id )) {
                order.getPlace().getCalendar().deleteBookedDate( LocalDate.now() );
                this.placeService.savePlace( order.getPlace().getId() );

                order.getMasters().stream()
                        .forEach( master -> master.getCalendar().deleteBookedDate( LocalDate.now() ) );
                order.getMasters().stream()
                        .forEach( master -> this.masterService.saveMaster( master ) );

                order.setStatus( OrderStatus.COMPLETED );
                order.setFinishOfExecution( LocalDate.now() );
                try {
                    this.orderRepository.save( order );
                } catch (Exception e) {
                    log.error( "failed to save order! " +e);
                    throw new RuntimeException();

                }
            }
        }

    }

    public List <Order> getOrders() {
        return this.orderRepository.findAll();
    }
    /*TODO add to ui*/
    public List <Order> getOrdersByPrice(OrderStatus status) {
        Comparator <Order> priceComparator = Comparator.comparing( o -> o.getTotalPrice() );
        List <Order> sortedList = null;
        try {
            sortedList = this.orderRepository.findAll();
        } catch (Exception e) {
            log.error( "failed to find orders! " +e);
            throw new RuntimeException();

        }
        Collections.sort( sortedList, priceComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersByBookedDate(OrderStatus status) {
        Comparator <Order> dateOfBookingComparator = Comparator.comparing( o -> o.getDateBooked() );
        List <Order> sortedList = null;
        try {
            sortedList = this.orderRepository.findAll();
        } catch (Exception e) {
            log.error( "failed to find orders! " +e);
            throw new RuntimeException();

        }
        Collections.sort( sortedList, dateOfBookingComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status ).collect( Collectors.toList() );
    }

    public List <Order> getOrdersByExecutionDate(OrderStatus status) {
        Comparator <Order> dateOfExecutionComparator = Comparator.comparing( o -> o.getStartOfExecution() );
        List <Order> sortedList = null;
        try {
            sortedList = this.orderRepository.findAll();
        } catch (Exception e) {
            log.error( "failed to find orders! " +e);
            throw new RuntimeException();

        }
        Collections.sort( sortedList, dateOfExecutionComparator );
        return sortedList.stream()
                .filter( o -> o.getStatus() == status )
                .collect( Collectors.toList() );
    }

    public List <Order> getOrdersForPeriod(LocalDate start, LocalDate end) {
        List<Order> res = null;
        try {
            res = this.orderRepository.findAll();
        } catch (Exception e) {
            log.error( "failed to find orders! " +e);
            throw new RuntimeException();

        }
        return res.stream()
                .filter( o -> o.getStartOfExecution()
                        .compareTo( start ) >= 0 && o.getFinishOfExecution().compareTo( end ) <= 0 )
                .collect( Collectors.toList() );
    }

    @Override
    public void loadFromCsv() {
        try {
            List <Order> orderList = CsvOrderParser.load();
            for (Order order : orderList) {
                saveOrder( order );
            }
        } catch (IOException e) {
            log.error( "Check a path to the file!" );
            throw new RuntimeException();

        }
    }

    @Override
    public void exportToCsv() {

        try {
            CsvOrderWriter.writeOrders( getOrders() );
            System.out.println( getOrders().size() + " orders were successfully written to csv file!" );
        } catch (IOException e) {
            log.error( "Check a path to the file!" );
        }
    }

    @Override
    public void loadOrdersFromJson() {
        try {
            List <Order> orderList = GsonOrderParser.load();
            for (Order order : orderList) {
                saveOrder( order );
            }
        } catch (IOException e) {
            log.error( "Check a path to the file!" );
        }
    }

    @Override
    public void exportOrdersToJson() {
        try {
            GsonOrderWriter.serializeOrders( getOrders() );
        } catch (IOException e) {
            log.error( "Check a path to the file!" );
        }
    }

}
