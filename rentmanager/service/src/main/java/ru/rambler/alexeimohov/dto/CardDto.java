package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDto {

    private String id;

    private String expirationDate;

    private String validFromDate;

    private String creditCardNumber;
    
    private String availableFunds;

    private String user;
}
