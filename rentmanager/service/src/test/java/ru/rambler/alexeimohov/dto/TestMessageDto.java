package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.entities.Message;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestMessageDto {
    @Autowired
    private MessageMapper messageMapper;

    private Message message;

    private MessageDto messageDto;

    @BeforeEach
    void init() {
        this.message = TestEntitiesFactory.getMessage();
        this.messageDto = TestEntitiesFactory.getMessageDto();
    }

    @Test
    void convertEntityToDtoAndExpectConsistency() {
      MessageDto converted = messageMapper.toDto( message );
        Assertions.assertEquals( "7475", converted.getId() );
        Assertions.assertEquals( "547469", converted.getUserId() );
    }

    @Test
    void convertDtoToEntityAndExpectConsistency() {
      Message converted = messageMapper.fromDto( messageDto );
        Assertions.assertEquals( 2352l, converted.getId() );
        Assertions.assertEquals( 2l, converted.getUser().getId() );

    }
}
