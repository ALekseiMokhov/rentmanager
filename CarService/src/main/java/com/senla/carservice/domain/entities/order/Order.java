package com.senla.carservice.domain.entities.order;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private UUID id;
    private OrderStatus status;
    private LocalDate dateBooked;
    private LocalDate startOfExecution;
    private LocalDate finishOfExecution;
    private List <IMaster> masters;
    private Place place;

    public Order(LocalDate dateBooked, LocalDate startOfExecution, List <IMaster> masters, Place place) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setMaster(IMaster old, IMaster current) {
        this.masters.set( masters.indexOf( old ), current );
    }

    public Place getPlace() {
        return this.place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


    public LocalDate getDateBooked() {
        return this.dateBooked;
    }

    public List <IMaster> getMasters() {
        return this.masters;
    }

    public void setMasters(List <IMaster> masters) {
        this.masters = masters;
    }

    public LocalDate getStartOfExecution() {
        return this.startOfExecution;
    }

    public LocalDate getFinishOfExecution() {
        return finishOfExecution;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    public void setStartOfExecution(LocalDate startOfExecution) {
        this.startOfExecution = startOfExecution;
    }

    public void setFinishOfExecution(LocalDate finishOfExecution) {
        this.finishOfExecution = finishOfExecution;
    }

    public double getTotalPrice() {
        return this.masters
                .stream()
                .mapToDouble( master -> master.getDailyPayment() )
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getStatus() == order.getStatus() &&
                Objects.equals( getId(), order.getId() ) &&
                Objects.equals( getDateBooked(), order.getDateBooked() ) &&
                Objects.equals( getStartOfExecution(), order.getStartOfExecution() ) &&
                Objects.equals( getFinishOfExecution(), order.getFinishOfExecution() ) &&
                Objects.equals( getMasters(), order.getMasters() ) &&
                Objects.equals( getPlace(), order.getPlace() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( getStatus(), getId(), getDateBooked(), getStartOfExecution(), getFinishOfExecution(), getMasters(), getPlace() );
    }

    @Override
    public String toString() {
        return "Order {" +
                "status=" + status +
                ", id=" + id +
                ", dateBooked=" + dateBooked +
                ", startOfExecution=" + startOfExecution +
                ", finishOfExecution=" + finishOfExecution +
                ", masters=" + masters.stream().map( m -> m.toString() ).collect( Collectors.toList() ) +
                ", place=" + place +
                '}';
    }
}
