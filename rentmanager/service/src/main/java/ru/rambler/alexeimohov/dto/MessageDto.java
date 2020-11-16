package ru.rambler.alexeimohov.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {

    String id;

    String text;

    UserDto user;

    String dateTineOfSending;
}
