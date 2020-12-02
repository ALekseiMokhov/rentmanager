package ru.rambler.alexeimohov.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.rambler.alexeimohov.dto.mappers.interfaces.SubscriptionMapper;
import ru.rambler.alexeimohov.entities.Subscription;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestSubscriptionDto {
    @Autowired
    private SubscriptionMapper subscriptionMapper;

    private Subscription subscription;

    private SubscriptionDto subscriptionDto;

    @BeforeEach
    void init() {
        this.subscription = TestEntitiesFactory.getSubscription();
        this.subscriptionDto = TestEntitiesFactory.getSubscriptionDto();
    }

    @Test
    void convertEntityToDtoAndExpectConsistency() {
        SubscriptionDto converted = subscriptionMapper.toDto( subscription );
        Assertions.assertEquals( "2040-12-31", converted.getExpirationDate() );
        Assertions.assertEquals( "Sergei", converted.getUser().getUsername() );
    }

    @Test
    void convertDtoToEntityAndExpectConsistency() {
        Subscription converted = subscriptionMapper.fromDto( subscriptionDto );
        Assertions.assertEquals( "2040-12-31", converted.getExpirationDate().toString() );

    }
}
