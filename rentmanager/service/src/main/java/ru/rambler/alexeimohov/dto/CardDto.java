package ru.rambler.alexeimohov.dto;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CardDto {

    private String id;
    @NotNull
    private String expirationDate;
    @NotNull
    private String validFromDate;
    @NotNull
    @CreditCardNumber
    private String creditCardNumber;
    @NotNull
    private String availableFunds;
    @NotNull
    private String blockedFunds;
    @NotNull
    private String userId;
}
