package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleDto {

    private String id;

    private String modelName;

    private String isHumanPowered;

    private String isChildish;

    private String rentPrice;

    private String finePrice;

    private String type;

    private RentPointDto rentPoint;
}
