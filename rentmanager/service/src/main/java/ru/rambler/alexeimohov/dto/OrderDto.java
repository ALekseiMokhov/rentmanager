package ru.rambler.alexeimohov.dto;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDto {
    private String id;

    private String creationTime;
    @NotNull
    private String startTime;

    private String finishTime;
    @NotNull
    @CreditCardNumber
    private String creditCardNumber;
    
    private String blockedFunds;

    private String totalPrice;

    private String hasValidSubscription;

    private String status;
    @NotNull
    private UserDto userDto;
    @NotNull
    private VehicleDto vehicleDto;


}
