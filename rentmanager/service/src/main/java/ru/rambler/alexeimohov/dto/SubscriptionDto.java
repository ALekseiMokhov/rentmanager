package ru.rambler.alexeimohov.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SubscriptionDto {
    private String id;
    @NotNull
    private UserDto user;
    @NotNull
    private String price;
    @NotNull
    private String orderDate;
    @NotNull
    private String startDate;
    @NotNull
    private String expirationDate;

}
