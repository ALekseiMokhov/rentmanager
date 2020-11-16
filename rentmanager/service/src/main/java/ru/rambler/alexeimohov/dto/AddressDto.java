package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String id;

    private String city;

    private String buildingNumber;

    private String street;
    
    private String rentPoint;
}
