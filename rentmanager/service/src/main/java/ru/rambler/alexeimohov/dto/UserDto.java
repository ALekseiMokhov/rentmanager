package ru.rambler.alexeimohov.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserDto {
    private String id;

    private String fullName;

    private String password;

    private String email;

    private String phoneNumber;

    private String role;

    private String privilege;

}
