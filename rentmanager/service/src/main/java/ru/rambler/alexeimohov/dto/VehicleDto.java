package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleDto {

    private String id;

    private String modelName;

    private String isHumanPowered;

    private String isChildish;

    private String rentPrice;

    private String finePrice;

    private String type;

    private String rentPoint;
}
