package com.senla.carservice.domain.entities.order;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.AbstractMaster;
import com.senla.carservice.domain.entities.master.IMaster;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orderz")     /*TODO delete after migrating from H2*/
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated
    private OrderStatus status;
    private LocalDate dateBooked;
    private LocalDate startOfExecution;
    private LocalDate finishOfExecution;
    @OneToMany(targetEntity = AbstractMaster.class)
    private List <IMaster> masters;
    @OneToOne
    private Place place;

    public Order(LocalDate dateBooked, LocalDate startOfExecution, Place place, List <IMaster> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = UUID.randomUUID();
    }

    /* overloaded to inject id from remote sources*/
    public Order(UUID id, LocalDate dateBooked, LocalDate startOfExecution, Place place, List <IMaster> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = id;
    }

    public void setMaster(IMaster old, IMaster current) {
        this.masters.set( masters.indexOf( old ), current );
    }


    public double getTotalPrice() {
        return this.masters
                .stream()
                .mapToDouble( master -> master.getDailyPayment() )
                .sum();
    }


}
