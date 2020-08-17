package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import dependency.injection.annotations.Qualifier;
import dependency.injection.annotations.components.Component;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Qualifier
@Component
public class PlaceRepositoryJdbc implements IPlaceRepository {

    private final static String FIND_SQL = "SELECT * FROM PLACE WHERE ID = ?";
    private final static String FIND_ALL_SQL = "SELECT * FROM PLACE";
    private final static String SAVE_SQL = "INSERT INTO PLACE VALUES";
    private final static String DELETE_SQL = "Update developers SET salary WHERE specialty = ?";


    @SneakyThrows
    @Override
    public Place findById(UUID id) {
        try (Connection connection = DataSourceFactory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_SQL );

        ) {
            statement.setString( 1, String.valueOf( id ) );
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    UUID uuid = UUID.fromString( resultSet.getString( "id" ) );
                    Array array = resultSet.getArray( "calendar" );
                    List <LocalDate> dates = Arrays.asList( (LocalDate[]) array.getArray() );

                    Place place = new Place();
                    place.setId( uuid );
                    for (LocalDate date : dates) {
                        place.getCalendar().setDateForBooking( date );
                    }
                    return place;
                }
            }
            throw new SQLException() ;
        }
    }
    @Override
    public List <Place> findAll() {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void save(Place place) {

    }
}
