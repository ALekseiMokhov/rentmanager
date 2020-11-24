package ru.rambler.alexeimohov.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MessageDto {

    private String id;
    @NotNull
    @Max(1024)
    private String text;
    @NotNull
    private String userId;
    @NotNull
    private String dateTimeOfSending;
}
