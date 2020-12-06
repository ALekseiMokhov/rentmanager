package ru.rambler.alexeimohov.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
@EqualsAndHashCode(exclude = { "availableFunds", "blockedFunds" })
@ToString(exclude = "user")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration_date", columnDefinition = "DATE")
    private LocalDate expirationDate;

    @Column(name = "valid_date", columnDefinition = "DATE")
    private LocalDate validFromDate;

    @NotNull(message = "credit card shouldn't be null!")
    @Column(name = "credit_card_number", unique = true)
    private long creditCardNumber;

    @Column(name = "available_funds")
    private double availableFunds;

    @Column(name = "blocked_funds")
    private double blockedFunds;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "full_name",
            referencedColumnName = "full_name"
    )
    private User user;


}


