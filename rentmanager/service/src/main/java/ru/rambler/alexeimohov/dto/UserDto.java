package ru.rambler.alexeimohov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String hasValidSubscription;

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
