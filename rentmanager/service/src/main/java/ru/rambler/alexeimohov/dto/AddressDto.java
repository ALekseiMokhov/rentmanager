package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {

    private String id;

    private String city;

    private String buildingNumber;

    private String street;

}
