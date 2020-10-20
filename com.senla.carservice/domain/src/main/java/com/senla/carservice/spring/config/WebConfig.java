package com.senla.carservice.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan({"com.senla.carservice.controller",
        "com.senla.carservice.spring.exceptions"})
public class WebConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        log.debug(String.valueOf(applicationContext.getBeanDefinitionCount()));
        log.debug(String.valueOf(Arrays.asList(applicationContext.getBeanDefinitionNames())));

    }

    /*THYMELEAF profile*/
    @Bean
    @Profile("ui")
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }


    @Bean
    @Profile("ui")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    @Profile("ui")
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /*REST profile*/

    @Bean
    @Profile("rest")
    public View jsonTemplate() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

    @Bean
    @Profile("rest")
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }


}
