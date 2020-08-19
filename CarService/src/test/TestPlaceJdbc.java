import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.jdbc.DataSourceFactory;
import com.senla.carservice.repository.jdbc.PlaceRepositoryJdbc;
import dependency.injection.beanfactory.BeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import property.configurer.ConfigPropertyScanner;
import property.configurer.PropertyInjector;
import property.configurer.PropertyLoader;
import property.configurer.PropertyStorage;
import util.calendar.Calendar;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class TestPlaceJdbc {

    private final static Logger LOGGER = LoggerFactory.getLogger( TestPlaceJpa.class );
    PlaceRepositoryJdbc repo;
    DataSourceFactory factory;
    @BeforeEach
    public void init() throws Exception {


    }
    @BeforeEach
    void testConnection() throws SQLException {
        PropertyLoader.loadCustomProperties( "/config.properties" );
        factory = DataSourceFactory.getInstance();
        PropertyInjector.injectProperty( factory );
        
        
      DataSource dataSource = factory.getDatasource();
        System.out.println(dataSource==null);
      Connection connection = dataSource.getConnection();
        repo = new PlaceRepositoryJdbc();
    }
    @Test
    void testAddPlace(){

        this.repo.save( new Place( new Calendar() ) );
        Assertions.assertEquals( repo.findAll().size(),10 );
    }
      @Test
    void testDeletePlace(){
        this.repo.delete( UUID.fromString( "d8474daa-72cf-4c89-a21a-d8b60c32752b" )	 );
      }
}
