package ru.rambler.alexeimohov.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserDto {
    private String id;
    @NotNull
    private String fullName;
    @NotNull
    /*TODO check not to represent in view*/
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String role;
    @NotNull
    private String privilege;

}
