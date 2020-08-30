package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import dependency.injection.annotations.Qualifier;
import lombok.SneakyThrows;
import util.calendar.Calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.senla.carservice.repository.jdbc.SqlHolder.*;


@Qualifier
public class PlaceRepositoryJdbc implements IPlaceRepository {



    private DataSourceFactory factory = DataSourceFactory.getInstance();
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @SneakyThrows
    @Override
    public Place findById(UUID id) {
        Place place = new Place();
        try {
            connection = factory.getDatasource().getConnection();
            connection.setAutoCommit( false );
            statement = connection.prepareStatement( FIND_PLACE_SQL );
            statement.setString( 1, String.valueOf( id ) );
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();

                for (Object o : array) {
                    if (place.getCalendar() == null) {
                        place.setCalendar( new Calendar() );
                    }
                    place.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
            }
            place.setId( id );
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }

        return place;
    }

    @SneakyThrows
    @Override
    public List <Place> findAll() {
        List <Place> placeList = new ArrayList <>();
        try {
            connection = factory.getDatasource().getConnection();
            connection.setAutoCommit( false );
            statement = connection.prepareStatement( FIND_ALL_PLACES_SQL );
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Place place = new Place();
                place.setId( UUID.fromString( resultSet.getString( "id" ) ) );


                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();
                place.setCalendar( new Calendar() );
                for (Object o : array) {
                    place.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                placeList.add( place );

            }
            connection.commit();
            connection.close();
        }catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit( true );
            throw e;
        }
        return placeList;


    }

    @Override
    @SneakyThrows
    public void delete(UUID id) {
        try  {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( DELETE_PLACE_SQL );
            statement.setString( 1, String.valueOf( id ) );
            statement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit( true );
            throw e;
        }
    }

    @Override
    @SneakyThrows
    public void save(Place place) {

        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( SAVE_PLACE_SQL );

            statement.setString( 1, String.valueOf( place.getId() ) );
            if (place.getCalendar() == null) {
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", new Object[ 0 ] ) );
            } else {
                Object[] localDates = place.getCalendar().getBookedDates().keySet().toArray();
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", localDates ) );
            }
            statement.executeUpdate();
            connection.commit();
            connection.close();

        }
        catch (SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
    }


}
