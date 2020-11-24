package ru.rambler.alexeimohov.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleDto {

    private String id;
    @NotNull
    private String modelName;
    @NotNull
    private String isHumanPowered;
    @NotNull
    private String isChildish;
    @NotNull
    private String rentPrice;
    @NotNull
    private String finePrice;
    @NotNull
    private String type;
    @NotNull
    private RentPointDto rentPoint;
}
