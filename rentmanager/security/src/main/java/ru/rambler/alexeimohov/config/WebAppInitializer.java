package ru.rambler.alexeimohov.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.rambler.alexeimohov.spring.PersistenceConfig;
import ru.rambler.alexeimohov.spring.UtilConfig;
import ru.rambler.alexeimohov.spring.WebConfig;
  /*
  * Spring configuration class located in top module to avoid circular dependencies*/
@EnableWebMvc
@Configuration
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class <?>[] getRootConfigClasses() {
        return new Class[]{ PersistenceConfig.class, UtilConfig.class, CustomSecurityConfig.class };

    }

    @Override
    protected Class <?>[] getServletConfigClasses() {
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
        return context;

    }


}
