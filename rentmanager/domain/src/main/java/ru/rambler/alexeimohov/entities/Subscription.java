package ru.rambler.alexeimohov.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/*
 *
 * Entity @linked to User.
 * IOrderService ignores setting totalPrice for User with valid @field Subscription */
@Entity
@Table(name = "subscription")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "subscription", fetch = FetchType.LAZY)
    private User user;

    @NotNull(message = "The subscribe must have price!")
    private double price;

    @NotNull
    private boolean isExpired;

    @Column(name = "date_order")
    @NotNull(message = "The order date can't be null!")
    private LocalDate orderDate;

    @Column(name = "date_beginning")
    @NotNull(message = "The beginning date should be selected!")
    private LocalDate startDate;

    @Column(name = "date_expired")
    @NotNull(message = "The expiration date should be selected")
    private LocalDate expirationDate;

    public Subscription() {
        this.orderDate = LocalDate.now();
    }

    public boolean isExpired() {
        return expirationDate.isAfter(LocalDate.now());
    }
}
