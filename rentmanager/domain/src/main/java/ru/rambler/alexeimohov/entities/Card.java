package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
/*
 * User's credit card 1:1*/

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
}


