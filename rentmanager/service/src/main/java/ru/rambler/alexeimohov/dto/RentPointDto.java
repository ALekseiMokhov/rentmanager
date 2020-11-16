package ru.rambler.alexeimohov.dto;

import lombok.*;

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
