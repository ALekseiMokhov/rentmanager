package ru.rambler.alexeimohov.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value = "ru.rambler.alexeimohov",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.REGEX,pattern =  ".*[Test]" )})

@PropertySource("application.properties")
@EnableTransactionManagement
public class PersistenceConfig {

    @Autowired
    private Environment env;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource() );
        em.setPackagesToScan( new String[]{ "ru.rambler.alexeimohov.entities" } );

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter( vendorAdapter );
        em.setJpaProperties( additionalProperties() );

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( env.getProperty( "db.driver" ) );
        dataSource.setUrl( env.getProperty( "db.url" ) );
        dataSource.setUsername( env.getProperty( "db.user" ) );
        dataSource.setPassword( env.getProperty( "db.password" ) );
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );

        return transactionManager;
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty( "hibernate.hbm2ddl.auto", env.getProperty( "hibernate.hbm2ddl.auto" ) );
        properties.setProperty( "hibernate.dialect", env.getProperty( "hibernate.dialect" ) );
        properties.setProperty( "hibernate.show_sql", env.getProperty( "hibernate.show_sql" ) );
        properties.setProperty( "hibernate.format_sql", env.getProperty( "hibernate.format_sql" ) );

        return properties;
    }
}
