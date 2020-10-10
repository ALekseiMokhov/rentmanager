package com.senla.carservice.spring;

import com.senla.carservice.spring.config.DomainConfig;
import com.senla.carservice.spring.config.RestConfig;
import com.senla.carservice.spring.config.WebAppInitializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes =
        {WebAppInitializer.class, RestConfig.class, DomainConfig.class})
@ActiveProfiles(profiles = "localtest")
public class TestSpringProfiles {

    @Test
    void shouldSetCorrectProfile() {

    }
}
