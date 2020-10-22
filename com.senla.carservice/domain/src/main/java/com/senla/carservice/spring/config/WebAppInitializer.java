package com.senla.carservice.spring.config;


import com.senla.carservice.security.SpringSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@EnableWebMvc
@Configuration
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DomainConfig.class, SpringSecurityConfig.class};

    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {

        WebApplicationContext context =
                (WebApplicationContext) super.createRootApplicationContext();
        ((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles("rest");

        return context;

    }


}
