package com.senla.carservice.service;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.dto.OrderDto;
import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.dto.mappers.OrderMapper;
import com.senla.carservice.dto.mappers.PlaceMapper;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.service.interfaces.IMasterService;
import com.senla.carservice.service.interfaces.IOrderService;
import com.senla.carservice.service.interfaces.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {
    @Autowired
    @Qualifier("orderJpaRepository")
    private IGenericRepository<Order> repository;
    @Autowired
    @Qualifier("masterService")
    private IMasterService masterService;
    @Autowired
    @Qualifier("placeService")
    private IPlaceService placeService;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private MasterMapper masterMapper;
    @Autowired
    private OrderMapper orderMapper;


    public OrderService() {
    }


    @Transactional
    public void addOrder(LocalDate date, LocalDate startOfExecution, Set<Speciality> required) {
        List<Master> availableMasters = new ArrayList<>();
        for (Speciality speciality : required) {
            Master master = this.masterMapper.
                    masterFromDto(masterService.getFreeBySpeciality(startOfExecution, speciality));
            availableMasters.add(master);
            masterService.setMasterForDate(master.getId(), startOfExecution);

        }
        Place place = this.placeMapper.dtoToPlace(this.placeService.getFreePlaceDto(startOfExecution));
        this.placeService.setPlaceForDate(place.getId(), startOfExecution);
        Order order = new Order(date, startOfExecution, place, availableMasters);
        order.setStatus(OrderStatus.MANAGED);

        this.repository.save(order);


    }

    @Override
    public void saveOrder(OrderDto orderDto, List<MasterDto> masterDtos, PlaceDto placeDto) {
        Order order = this.orderMapper.orderFromDto(orderDto);
        order.setMasters(this.masterMapper.dtoToMasters(masterDtos));
        order.setPlace(this.placeMapper.dtoToPlace(placeDto));
        if (this.repository.getById(order.getId()) != null) {
            this.repository.update(order);
        } else this.repository.save(order);
        /*TODO check data consistency with masters and place*/

    }

    public OrderDto findOrderById(UUID id) {

        return this.orderMapper.dtoFromOrder(this.repository.getById(id)) ;

    }

    @Override
    public void deleteOrder(UUID id) {

        this.repository.delete(id);

    }

    public void shiftOrderExecutionDate(UUID id, LocalDate newDate) {
        Order order = this.repository.getById(id);
        LocalDate oldDate = order.getStartOfExecution();

        Place oldPlace = order.getPlace();
        Place newPlace = this.placeMapper.dtoToPlace(this.placeService.getFreePlaceDto(newDate));

        List<Master> oldMasters = order.getMasters();
        Set<Speciality> required = oldMasters.stream()
                .map(m -> m.getSpeciality())
                .collect(Collectors.toSet());
        List<Master> newMasters = new ArrayList<>();

        this.placeService.setPlaceFree(oldPlace.getId(), oldDate);

        newPlace.getCalendar().setDateForBooking(newDate);


        oldMasters.stream()
                .forEach(master -> master.getCalendar()
                        .deleteBookedDate(oldDate));

        for (Speciality speciality : required) {
            Master master = this.masterMapper.
                    masterFromDto(masterService.getFreeBySpeciality(newDate, speciality));
            newMasters.add(master);
            masterService.setMasterForDate(master.getId(), newDate);

        }
        order.setPlace(newPlace);
        order.setMasters(newMasters);
        order.setStartOfExecution(newDate);

    }

    public void setNewMasters(UUID id) {
        Order order = this.repository.getById(id);
        List<Master> newMasters = new ArrayList<>();

        for (Master master : order.getMasters()) {
            Master master1 = this.masterMapper.masterFromDto(this.masterService
                    .getFreeBySpeciality(order.getStartOfExecution(), master.getSpeciality()));
            this.masterService.setMasterForDate(master1.getId(), order.getStartOfExecution());
            newMasters.add(master1);

            this.masterService.setBookedDateFree(master.getId(), order.getStartOfExecution());
        }
        order.setMasters(newMasters);

    }

    public void cancelOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order.getId().equals(id)) {
                order.setStatus(OrderStatus.CANCELLED);
                order.getMasters().stream()
                        .forEach(master -> master.getCalendar().deleteBookedDate(order.getStartOfExecution()));
                order.getMasters()
                        .stream()
                        .forEach(master -> this.masterService.saveMaster(this.masterMapper.masterToDto(master)));

                Place place = order.getPlace();
                this.placeService.setPlaceFree(place.getId(), order.getStartOfExecution());
                this.placeService.savePlace(this.placeMapper.placeToDto(place));


            }
        }

    }

    @Transactional
    public void completeOrder(UUID id) {
        for (Order order : this.repository.findAll()) {
            if (order.getId().equals(id)) {
                order.getPlace().getCalendar().deleteBookedDate(LocalDate.now());

                order.getMasters().stream()
                        .forEach(master -> master.getCalendar().deleteBookedDate(LocalDate.now()));


                order.setStatus(OrderStatus.COMPLETED);
                order.setFinishOfExecution(LocalDate.now());

            }
        }

    }

    public List<OrderDto> getOrders() {
        return this.orderMapper.dtoFromOrders(this.repository.findAll());
    }


    public List<OrderDto> getOrdersByPrice(OrderStatus status) {
        Comparator<Order> priceComparator = Comparator.comparing(o -> o.getTotalPrice());
        List<Order> sortedList = this.repository.findAll();
        Collections.sort(sortedList, priceComparator);
        sortedList = sortedList.stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
        return this.orderMapper.dtoFromOrders(sortedList);
    }

    public List<OrderDto> getOrdersByBookedDate(OrderStatus status) {
        Comparator<Order> dateOfBookingComparator = Comparator.comparing(o -> o.getDateBooked());
        List<Order> sortedList = this.repository.findAll();
        Collections.sort(sortedList, dateOfBookingComparator);
        sortedList = sortedList.stream()
                .filter(o -> o.getStatus() == status).collect(Collectors.toList());
        return this.orderMapper.dtoFromOrders(sortedList);
    }

    public List<OrderDto> getOrdersByExecutionDate(OrderStatus status) {
        Comparator<Order> dateOfExecutionComparator = Comparator.comparing(o -> o.getStartOfExecution());
        List<Order> sortedList = null;

        sortedList = this.repository.findAll();


        Collections.sort(sortedList, dateOfExecutionComparator);
        sortedList = sortedList.stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
        return this.orderMapper.dtoFromOrders(sortedList);
    }

    public List<OrderDto> getOrdersForPeriod(LocalDate start, LocalDate end) {
        List<Order> res = this.repository.findAll();

        res = res.stream()
                .filter(o -> o.getStartOfExecution()
                        .compareTo(start) >= 0 && o.getFinishOfExecution().compareTo(end) <= 0)
                .collect(Collectors.toList());
        return this.orderMapper.dtoFromOrders(res);
    }


}
