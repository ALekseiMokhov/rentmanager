package ru.rambler.alexeimohov.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {

    private String id;
    @NotNull
    private String city;
    @NotNull
    private String buildingNumber;
    @NotNull
    private String street;
    @NotNull
    private RentPointDto rentPoint;

}
