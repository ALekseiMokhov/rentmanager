package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import dependency.injection.annotations.Qualifier;
import lombok.SneakyThrows;
import util.calendar.Calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Qualifier
public class PlaceRepositoryJdbc implements IPlaceRepository {

    private final static String FIND_SQL = "SELECT * FROM SENLA.PLACE WHERE ID = ?";
    private final static String FIND_ALL_SQL = "SELECT * FROM SENLA.PLACE";
    private final static String SAVE_SQL = "INSERT INTO SENLA.PLACE (id,calendar) VALUES (?,?)";
    private final static String DELETE_SQL = "DELETE FROM SENLA.PLACE WHERE ID = ?";

    private DataSourceFactory factory = DataSourceFactory.getInstance();

    @SneakyThrows
    @Override
    public Place findById(UUID id) {
        Place place = new Place();
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_SQL );

        ) {

            statement.setString( 1, String.valueOf( id ) );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();

                for (Object o : array) {
                    place.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
            }
            place.setId( id );

        }

        return place;
    }

    @SneakyThrows
    @Override
    public List <Place> findAll() {
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_ALL_SQL );
             ResultSet resultSet = statement.executeQuery();
        ) {
            System.out.println( resultSet.getFetchSize() );
            List <Place> placeList = new ArrayList <>();
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
            return placeList;
        }

    }

    @Override
    @SneakyThrows
    public void delete(UUID id) {
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( DELETE_SQL );

        ) {
            statement.setString( 1, String.valueOf( id ) );
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void save(Place place) {

        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( SAVE_SQL );


        ) {
            
                statement.setString( 1, String.valueOf( place.getId() ) );
                if (place.getCalendar() == null) {
                    statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", new Object[ 0 ] ) );
                } else {
                    Object[] localDates = place.getCalendar().getBookedDates().keySet().toArray();
                    statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", localDates ) );
                }
                statement.executeUpdate();

        }

    }
}
