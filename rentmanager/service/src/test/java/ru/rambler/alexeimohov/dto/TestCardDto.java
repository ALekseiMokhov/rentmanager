package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.entities.Card;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestCardDto {
    @Autowired
    private CardMapper cardMapper;

    private Card card;

    private CardDto cardDto;

    @BeforeEach
    void init() {
        this.card = TestEntitiesFactory.getCard();
        this.cardDto = TestEntitiesFactory.getCardDto();

    }

    @Test
    void convertEntityToDtoAndExpectConsistency() {
        CardDto converted = cardMapper.toDto( card );
        Assertions.assertEquals( "3333.4", converted.getAvailableFunds() );
        Assertions.assertEquals( "2040-12-31", converted.getExpirationDate() );
        Assertions.assertEquals( "547469", converted.getUserId() );
    }

    @Test
    void convertDtoToEntityAndExpectConsistency() {
        Card converted = cardMapper.fromDto( cardDto );
        Assertions.assertEquals( 232, converted.getAvailableFunds() );
        Assertions.assertEquals( 1111111111111111l, converted.getCreditCardNumber() );
        Assertions.assertEquals( 547469l, converted.getUser().getId() );
    }
}
