package ru.rambler.alexeimohov.entities;

import lombok.*;
import ru.rambler.alexeimohov.entities.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
 * Order entity with embedded enum OrderStatus
 *  n:1 User  unidirectional
 * n:1 Vehicle    unidirectional
 * n:1 RentPoint unidirectional*/
@Entity
@Table(name = "`order`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    @NotNull(message = "Order must contain creation time!")
    private LocalDateTime creationTime;

    @Column(name = "start_time")
    @NotNull(message = "Order must have time of the beginning!")
    private LocalDateTime startTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column(name = "card_number")
    @NotNull(message = "Card can'tbe null!")
    private long creditCardNumber;

    @Column(name = "blocked_funds")
    @NotNull(message = "Order is processed after blocking user funds!")
    private double blockedFunds;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "has_valid_subscription")
    @NotNull
    private boolean hasValidSubscription;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    @NotNull
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;

    public Order() {
        this.status = OrderStatus.CREATED;
    }
}
