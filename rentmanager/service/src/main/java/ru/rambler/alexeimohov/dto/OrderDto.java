package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDto {
    private String id;

    private String creationTime;

    private String startTime;

    private String finishTime;

    private String creditCardNumber;

    private String blockedFunds;

    private String totalPrice;

    private String hasValidSubscription;

    private String status;
    
    private UserDto userDto;

    private VehicleDto vehicleDto;


}
