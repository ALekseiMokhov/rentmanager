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
    private String userName;
    @NotNull
    private String price;
    @NotNull
    private String orderDate;
    @NotNull
    private String startDate;
    @NotNull
    private String expirationDate;

}
