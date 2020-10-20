package com.senla.carservice.spring;

import com.senla.carservice.dto.mappers.OrderStatusMapper;
import com.senla.carservice.dto.mappers.SpecialityMapper;
import com.senla.carservice.dto.mappers.UuidMapper;
import com.senla.carservice.dto.mappers.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public PlaceMapper placeMapper() {
        PlaceMapper mapper = new PlaceMapperImpl();
        return mapper;
    }

    @Bean
    public MasterMapper masterMapper() {
        MasterMapper mapper = new MasterMapperImpl();
        return mapper;
    }

    @Bean
    public OrderMapper orderMapper() {
        OrderMapper mapper = new OrderMapperImpl();
        return mapper;
    }

    @Bean
    public UuidMapper uuidMapper() {
        return new UuidMapper();
    }

    @Bean
    public SpecialityMapper specialityMapper() {
        return new SpecialityMapper();
    }

    @Bean
    public OrderStatusMapper statusMapper() {
        return new OrderStatusMapper();
    }
}
