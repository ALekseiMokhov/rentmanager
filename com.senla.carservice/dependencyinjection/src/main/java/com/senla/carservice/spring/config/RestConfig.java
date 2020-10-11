package com.senla.carservice.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableWebMvc
/*@Profile("rest")*/
@PropertySource("classpath:application.properties")
@ComponentScan({"com.senla.carservice.controller"})
public class RestConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public RestConfig(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
       log.info(String.valueOf(Arrays.asList(applicationContext.getBeanDefinitionNames())));

    }

    @Bean
    public View jsonTemplate() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

}
