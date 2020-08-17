package com.senla.carservice.repository.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dependency.injection.annotations.components.Component;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import property.configurer.annotations.ConfigProperty;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class DataSourceFactory {
    private static DataSource instance;
    @ConfigProperty( propertyName = "DB_NAME")
    private static String DB_NAME;
    private final static Logger LOGGER = LoggerFactory.getLogger( DataSourceFactory.class );


    public static DataSource getDatasource() {
        if (instance == null) {
            switch (DB_NAME) {
                case "h2" -> instance = getH2DataSource();
                case "postgres" -> instance = getPostgresDataSource();
                default -> instance = getMySQLDataSource();
            }

        }
        return instance;
    }

    private static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mySqlDataSource = null;
        try {
            fis = new FileInputStream( "jdbcConfig.properties" );
            props.load( fis );
            mySqlDataSource = new MysqlDataSource();
            mySqlDataSource.setURL( props.getProperty( "mysql.driver" ) );
            mySqlDataSource.setUser( props.getProperty( "mysql.user" ) );
            mySqlDataSource.setPassword( props.getProperty( "mysql.password" ) );
        } catch (IOException e) {
            LOGGER.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return mySqlDataSource;
    }

    private static DataSource getPostgresDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        PGSimpleDataSource pgDataSource = null;
        try {
            fis = new FileInputStream( "jdbcConfig.properties" );
            props.load( fis );
            pgDataSource = new PGSimpleDataSource();
            pgDataSource.setURL( props.getProperty( "postgres.driver" ) );
            pgDataSource.setUser( props.getProperty( "postgres.user" ) );
            pgDataSource.setPassword( props.getProperty( "postgres.password" ) );
        } catch (IOException e) {
            LOGGER.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return pgDataSource;
    }

    private static DataSource getH2DataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        JdbcDataSource h2DataSource = null;
        try {
            fis = new FileInputStream( "jdbcConfig.properties" );
            props.load( fis );
            h2DataSource = new JdbcDataSource();
            h2DataSource.setURL( props.getProperty( "h2.driver" ) );
            h2DataSource.setUser( props.getProperty( "h2.user" ) );
            h2DataSource.setPassword( props.getProperty( "h2.password" ) );
        } catch (IOException e) {
            LOGGER.error( e.getLocalizedMessage() + " ERROR FROM DATASOURCE FACTORY" );
        }
        return h2DataSource;
    }

}
