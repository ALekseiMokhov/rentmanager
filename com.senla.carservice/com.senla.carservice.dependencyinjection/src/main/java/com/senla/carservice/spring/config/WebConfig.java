package com.senla.carservice.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.senla.carservice")
public class WebConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext( applicationContext );
        templateResolver.setPrefix( "classpath:/templates/" );
        templateResolver.setTemplateMode( "HTML5" );
        templateResolver.setSuffix( ".html" );
        return templateResolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver( templateResolver() );
        templateEngine.setEnableSpringELCompiler( true );
        return templateEngine;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver =
                new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty( "DatabaseException", "databaseError" );
        mappings.setProperty( "NullPointerException", "error" );

        exceptionResolver.setExceptionMappings( mappings );
        exceptionResolver.setDefaultErrorView( "error" );
        exceptionResolver.setExceptionAttribute( "ex" );
        exceptionResolver.setWarnLogCategory( "example.MvcLogger" );
        return exceptionResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine( templateEngine() );
        registry.viewResolver( resolver );
    }
}
