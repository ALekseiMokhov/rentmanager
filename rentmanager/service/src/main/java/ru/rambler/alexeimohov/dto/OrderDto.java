package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String id;

    private String creationTime;

    private String finishTime;

    private String blockedFunds;

    private String totalPrice;

    private String hasValidSubscription;

    private String status;
    
    private String user;

    private String vehicle;


}
