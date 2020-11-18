package ru.rambler.alexeimohov.dto;

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
    void init(){
       subscription = TestEntitiesFactory.getSubscription();
       subscriptionDto = TestEntitiesFactory.getSubscriptionDto();
    }
    @Test
    void convertEntityToDtoAndExpectConsistency(){

    }

    @Test
    void convertDtoToEntityAndExpectConsistency(){

    }
}
