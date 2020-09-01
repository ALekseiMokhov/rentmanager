package com.senla.carservice.repository.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.senla.carservice.properties.configurer.PropertyInjector;
import com.senla.carservice.properties.configurer.PropertyLoader;
import com.senla.carservice.properties.configurer.annotations.ConfigProperty;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DataSourceFactory {

    public static DataSourceFactory instance;
    @ConfigProperty(propertyName = "db.name")
    private String DB_NAME;
    @ConfigProperty(propertyName = "h2.url")
    private String URL;
    @ConfigProperty(propertyName = "h2.user")
    private String USER;
    @ConfigProperty(propertyName = "h2.password")
    private String PASSWORD;

    private DataSourceFactory() {

    }

    public static DataSourceFactory getInstance() {
        if (instance == null) {
            instance = new DataSourceFactory();
            PropertyLoader.loadCustomProperties( "/application.properties" );
            PropertyInjector.injectProperty( instance );
        }
        return instance;
    }

    public DataSource getDatasource() {

        DataSource dataSource = null;
        switch (DB_NAME) {
            case "h2" -> dataSource = getH2DataSource();
            case "postgres" -> dataSource = getPostgresDataSource();
            case "mysql" -> dataSource = getMySQLDataSource();
            default -> log.error( "ILLEGAL DB NAME IN PROPERTIES!DB NAME ACTUAL: " + DB_NAME );
        }
        log.debug( "IS DATASOURCE NULL: " + (dataSource == null) );
        return dataSource;
    }

    private DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mySqlDataSource = null;
        try {
            fis = new FileInputStream( "application.properties" );
            props.load( fis );
            mySqlDataSource = new MysqlDataSource();
            mySqlDataSource.setURL( props.getProperty( "mysql.url" ) );
            mySqlDataSource.setUser( props.getProperty( "mysql.user" ) );
            mySqlDataSource.setPassword( props.getProperty( "mysql.password" ) );
        } catch (IOException e) {
            log.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return mySqlDataSource;
    }

    private DataSource getPostgresDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        PGSimpleDataSource pgDataSource = null;
        try {
            fis = new FileInputStream( "application.properties" );
            props.load( fis );
            pgDataSource = new PGSimpleDataSource();
            pgDataSource.setURL( props.getProperty( "postgres.url" ) );
            pgDataSource.setUser( props.getProperty( "postgres.user" ) );
            pgDataSource.setPassword( props.getProperty( "postgres.password" ) );
        } catch (IOException e) {
            log.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return pgDataSource;
    }

    private DataSource getH2DataSource() {
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setURL( this.URL );
        h2DataSource.setUser( this.USER );
        h2DataSource.setPassword( this.PASSWORD );
        return h2DataSource;
    }

}
