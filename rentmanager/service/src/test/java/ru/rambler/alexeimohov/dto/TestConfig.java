package ru.rambler.alexeimohov.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapperImpl;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapperImpl;

@Configuration
@ComponentScan("ru.rambler.alexeimohov.dto")
public class TestConfig {
    @Bean
    RentPointMapper rentPointMapper(){
        return  new RentPointMapperImpl();
    }

    @Bean
    UserMapper userMapper(){return  new UserMapperImpl();}

}
