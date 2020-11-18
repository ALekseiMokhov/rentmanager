package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SubscriptionDto {
    private String id;

    private String userName;

    private String price;

    private String orderDate;

    private String startDate;

    private String expirationDate;

}
