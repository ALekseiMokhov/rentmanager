package ru.rambler.alexeimohov.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.dto.mappers.RentPointMapperImpl;

@Configuration
@ComponentScan("ru.rambler.alexeimohov.dto")
public class TestConfig {
    @Bean
    RentPointMapper rentPointMapper(){
        return  new RentPointMapperImpl();
    }
}
