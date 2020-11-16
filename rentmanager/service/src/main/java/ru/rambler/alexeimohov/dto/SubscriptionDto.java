package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
public class SubscriptionDto {
    private String id;

    private String user;

    private String price;

    private String orderDate;

    private String startDate;

    private String expirationDate;

}
