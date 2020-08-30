package com.senla.carservice.repository.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import property.configurer.PropertyInjector;
import property.configurer.PropertyLoader;
import property.configurer.annotations.ConfigProperty;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DataSourceFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger( DataSourceFactory.class );
    @ConfigProperty(propertyName = "db.name")
    private String DB_NAME;
    @ConfigProperty(propertyName = "h2.url")
    private String URL;
    @ConfigProperty(propertyName = "h2.user")
    private String USER;
    @ConfigProperty(propertyName = "h2.password")
    private String PASSWORD;

    public static DataSourceFactory instance;

    private DataSourceFactory() {

    }

    public static DataSourceFactory getInstance() {
        if (instance == null) {
            instance = new DataSourceFactory();
            PropertyLoader.loadCustomProperties( "/config.properties" );
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
            default -> LOGGER.error( "ILLEGAL DB NAME IN PROPERTIES!DB NAME ACTUAL: " + DB_NAME );
        }
        LOGGER.debug( "IS DATASOURCE NULL: " + (dataSource == null) );
        return dataSource;
    }

    private  DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mySqlDataSource = null;
        try {
            fis = new FileInputStream( "config.properties" );
            props.load( fis );
            mySqlDataSource = new MysqlDataSource();
            mySqlDataSource.setURL( props.getProperty( "mysql.url" ) );
            mySqlDataSource.setUser( props.getProperty( "mysql.user" ) );
            mySqlDataSource.setPassword( props.getProperty( "mysql.password" ) );
        } catch (IOException e) {
            LOGGER.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return mySqlDataSource;
    }

    private  DataSource getPostgresDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        PGSimpleDataSource pgDataSource = null;
        try {
            fis = new FileInputStream( "config.properties" );
            props.load( fis );
            pgDataSource = new PGSimpleDataSource();
            pgDataSource.setURL( props.getProperty( "postgres.url" ) );
            pgDataSource.setUser( props.getProperty( "postgres.user" ) );
            pgDataSource.setPassword( props.getProperty( "postgres.password" ) );
        } catch (IOException e) {
            LOGGER.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return pgDataSource;
    }

    private  DataSource getH2DataSource() {
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setURL( this.URL );
        h2DataSource.setUser( this.USER);
        h2DataSource.setPassword( this.PASSWORD);
        return h2DataSource;
    }

}
