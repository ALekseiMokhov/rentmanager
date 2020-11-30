package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.dto.UserDto;

@Getter
@Setter
public class UserRegisteredEvent {

    private UserDto userDto;

    public UserRegisteredEvent(UserDto userDto) {
        this.userDto = userDto;
    }
}
