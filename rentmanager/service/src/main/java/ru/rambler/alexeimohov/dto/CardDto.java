package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CardDto {

    private String id;

    private String expirationDate;

    private String validFromDate;

    private String creditCardNumber;
    
    private String availableFunds;

    private String userId;
}
