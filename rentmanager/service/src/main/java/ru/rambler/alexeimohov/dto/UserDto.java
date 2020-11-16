package ru.rambler.alexeimohov.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;

    private String fullName;

    private String password;

    private String email;

    private String phoneNumber;

    private String role;

    private String privilege;

    private List <MessageDto> messages;

    public UserDto() {
        this.messages = new ArrayList <>();
    }
}
