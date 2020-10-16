package com.senla.carservice.spring;

import com.senla.carservice.spring.config.DomainConfig;
import com.senla.carservice.spring.config.WebAppInitializer;
import com.senla.carservice.spring.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes =
        {WebAppInitializer.class, WebConfig.class, DomainConfig.class})
@ActiveProfiles(profiles = "localtest")
public class TestSpringProfiles {

    @Test
    void shouldSetCorrectProfile() {

    }
}
