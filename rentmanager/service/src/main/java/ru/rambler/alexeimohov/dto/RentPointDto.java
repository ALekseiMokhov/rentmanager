package ru.rambler.alexeimohov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentPointDto {
    private String id;

    private String pointName;

    private String type;

    private String coordinate;

}
