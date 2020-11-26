package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.enums.Role;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestUserDto {
    @Autowired
    private UserMapper userMapper;

    private User user;

    private UserDto userDto;

    @BeforeEach
    void init() {
        this.user = TestEntitiesFactory.getUser();
        this.userDto = TestEntitiesFactory.getUserDto();
    }

    @Test
    void ConvertUserToDtoAndExpectIdsOfMessages() {
        UserDto converted = userMapper.toDto( user );
        Assertions.assertEquals( "EXCLUSIVE", converted.getPrivilege() );
        Assertions.assertNull(  converted.getPassword());
    }

    @Test
    void reverseConvertFromDto() {
        User converted = userMapper.fromDto( userDto );
        Assertions.assertEquals( converted.getRole(), Role.USER );
        Assertions.assertNotNull(  converted.getPassword());

    }
}
