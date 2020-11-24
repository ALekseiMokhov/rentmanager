package ru.rambler.alexeimohov.spring;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@EnableWebMvc
@Configuration
@ComponentScan(value = "ru.rambler.alexeimohov",
        excludeFilters = { @ComponentScan.Filter(
                type = FilterType.REGEX, pattern = ".*[Test]") })
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class <?>[] getRootConfigClasses() {
        return new Class[]{ PersistenceConfig.class, UtilConfig.class /*SecurityConfig.class, */ };

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
