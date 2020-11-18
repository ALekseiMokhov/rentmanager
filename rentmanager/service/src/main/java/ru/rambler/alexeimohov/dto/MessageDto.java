package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MessageDto {

    private String id;

    private String text;

    private String userId;

    private String dateTimeOfSending;
}
