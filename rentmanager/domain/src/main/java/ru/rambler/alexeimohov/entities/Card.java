package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
/*
 * User's credit card n:1
 * Methods to add/write-off @param avaliableFunds
 * Methods to block-unblock @param blockedFunds*/

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration_date", columnDefinition = "Timestamp")
    private LocalDate expirationDate;

    @Column(name = "valid_date", columnDefinition = "Timestamp")
    private LocalDateTime validFromDate;

    @NotNull(message = "credit card shouldn't be null!")
    @CreditCardNumber(message = "Credit card should fit Luhn algorithm!")
    @Column(name = "credit_card_number")
    private long creditCardNumber;

    @Column(name = "available_funds")
    private double availableFunds;

    @Column(name = "blocked_funds")
    private double blockedFunds;


    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    private User user;

    public void addFunds(double amount) {
        availableFunds += amount;
    }

    public void writeOff(double amount) {
        if (amount > availableFunds) {
            throw new IllegalArgumentException( "Incorrect amount to write-off!" );
        }
        availableFunds -= amount;
    }

    public void blockFunds(double amount) {
        writeOff( amount );
        blockedFunds += amount;
    }

    public void unBlockFunds(double amount) {
        if (amount > blockedFunds) {
            throw new IllegalArgumentException( "Incorrect amount to unblock!" );

        }
        blockedFunds -= amount;
        addFunds( amount );
    }
}


