package com.senla.carservice.entity.order;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.Master;
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
            targetEntity = Master.class,
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<Master> masters;
    @OneToOne
    private Place place;

    public Order(LocalDate dateBooked, LocalDate startOfExecution, Place place, List<Master> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = UUID.randomUUID();
        this.status = OrderStatus.MANAGED;
    }

    public Order(UUID id, LocalDate dateBooked, LocalDate startOfExecution, Place place, List<Master> masters) {
        this.dateBooked = dateBooked;
        this.startOfExecution = startOfExecution;
        this.masters = masters;
        this.place = place;
        this.id = id;
    }

    public void setMaster(Master old, Master current) {
        this.masters.set(masters.indexOf(old), current);
    }


    public double getTotalPrice() {
        return this.masters
                .stream()
                .mapToDouble(master -> master.getDailyPayment())
                .sum();
    }


}
