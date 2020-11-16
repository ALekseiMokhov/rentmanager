package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.CycleAvoidingMappingContext;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestUserDto {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CycleAvoidingMappingContext context;

    private User user;

    private UserDto userDto;

    @BeforeEach
    void init() {
        user = TestEntitiesFactory.getUser();
        userDto = TestEntitiesFactory.getUserDto();
    }

    @Test
    void ConvertUserToDtoAndExpectIdsOfMessages() {
        UserDto converted = userMapper.toDto( user,context );
        Assertions.assertEquals(3,converted.getMessages().size());
    }

    void reverseConvertFromDto(){
        User converted = userMapper.fromDto( userDto,context ) ;
    }

}
