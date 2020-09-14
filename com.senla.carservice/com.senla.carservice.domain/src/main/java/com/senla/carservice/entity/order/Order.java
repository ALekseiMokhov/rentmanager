package com.senla.carservice.entity.order;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.AbstractMaster;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @Version
    private Long version;
    @Enumerated
    private OrderStatus status;
    private LocalDate dateBooked;
    private LocalDate startOfExecution;
    private LocalDate finishOfExecution;
    @OneToMany(
            targetEntity = AbstractMaster.class,
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List <AbstractMaster> masters;
    @OneToOne
    private Place place;

    public Order(LocalDate dateBooked, LocalDate startOfExecution, Place place, List <AbstractMaster> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = UUID.randomUUID();
    }
    
    public Order(UUID id, LocalDate dateBooked, LocalDate startOfExecution, Place place, List <AbstractMaster> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = id;
    }

    public void setMaster(AbstractMaster old, AbstractMaster current) {
        this.masters.set( masters.indexOf( old ), current );
    }


    public double getTotalPrice() {
        return this.masters
                .stream()
                .mapToDouble( master -> master.getDailyPayment() )
                .sum();
    }


}
