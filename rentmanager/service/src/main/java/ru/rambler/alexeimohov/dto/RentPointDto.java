package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentPointDto {
    private String id;
    @NotNull
    private String pointName;
    @NotNull
    private String type;
    @NotNull
    private String coordinate;

}
