package ru.rambler.alexeimohov.dto;

import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.enums.Privilege;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestEntitiesFactory {
    static User getUser(){
        User user = new User();
        user.setId( 547469l );
        user.setFullName( "Sergei" );
        user.setPassword( "tu4hd845cY23L" );
        user.setSubscription( new Subscription( 1l, user, 22.9, LocalDate.now(), LocalDate.now(), LocalDate.of( 2033, 11, 16 ) ) );
        user.setPrivilege( Privilege.EXCLUSIVE );
        user.addMessage( new Message( 1l, "Hi there!", user, LocalDateTime.now() ) );
        user.addMessage( new Message( 2l, "Second mssage", user, LocalDateTime.now() ) );
        user.addMessage( new Message( 3l, "The last message", user, LocalDateTime.now() ) );
        return user;
    }

    static UserDto getUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId( "2" );
        userDto.setFullName( "Alexander" );
        userDto.setPassword( "4f837t873T" );
        userDto.setPrivilege( "NEWBIE" );
        userDto.setRole( "USER" );
        userDto.setEmail( "Alex@gmail.com" );
        return userDto;
    }
}
