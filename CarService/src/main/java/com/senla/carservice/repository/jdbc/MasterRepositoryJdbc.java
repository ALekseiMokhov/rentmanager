package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.master.*;
import com.senla.carservice.repository.IMasterRepository;
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
public class MasterRepositoryJdbc implements IMasterRepository {
    private final static String FIND_SQL = "SELECT * FROM SENLA.MASTER WHERE ID = ?";
    private final static String FIND_ALL_SQL = "SELECT * FROM SENLA.MASTER";
    private final static String SAVE_SQL = "MERGE INTO SENLA.MASTER (id,calendar,fullname,dailypayment,speciality) VALUES (?,?,?,?,?)";
    private final static String DELETE_SQL = "DELETE FROM SENLA.MASTER WHERE ID = ?";

    private DataSourceFactory factory = DataSourceFactory.getInstance();

    @Override
    @SneakyThrows
    public IMaster findById(UUID id) {
        AbstractMaster master = null;
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_SQL );

        ) {

            statement.setString( 1, String.valueOf( id ) );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Speciality speciality = Speciality.valueOf( resultSet.getString( "speciality" ) );
                switch (speciality) {
                    case RESHAPER -> master = new Reshaper();
                    case PAINTER -> master = new Painter();
                    case MECHANIC -> master = new Mechanic();
                    case ELECTRICIAN -> master = new Electrician();
                    default -> throw new IllegalStateException( "Wrong speciality!" );
                }
                master.setSpeciality( speciality );

                master.setId( id );

                String fullName = resultSet.getString( "fullname" );
                master.setFullName( fullName );

                Double dailyPayment = resultSet.getDouble( "dailypayment" );
                master.setDailyPayment( dailyPayment );

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();
                for (Object o : array) {
                    if(master.getCalendar()==null){
                        master.setCalendar( new Calendar() );    
                    }
                    master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
            }

        }
        return master;
    }

    @Override
    @SneakyThrows
    public List <IMaster> findAll() {
        List <IMaster> result = new ArrayList <>();
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_ALL_SQL );
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                AbstractMaster master = null;
                Speciality speciality = Speciality.valueOf( resultSet.getString( "speciality" ) );
                switch (speciality) {
                    case RESHAPER -> master = new Reshaper();
                    case PAINTER -> master = new Painter();
                    case MECHANIC -> master = new Mechanic();
                    case ELECTRICIAN -> master = new Electrician();
                    default -> throw new IllegalStateException( "Wrong speciality!" );
                }
                master.setSpeciality( speciality );

                UUID id = UUID.fromString( resultSet.getString( "id" ) );
                master.setId( id );

                String fullName = resultSet.getString( "fullname" );
                master.setFullName( fullName );

                Double dailyPayment = resultSet.getDouble( "dailypayment" );
                master.setDailyPayment( dailyPayment );

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();
                master.setCalendar( new Calendar() );
                for (Object o : array) {
                    master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                result.add( master ) ;
            }
        }
        return result;
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
    public void save(IMaster master) {
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( SAVE_SQL );

        ) {
            statement.setString( 1, String.valueOf( master.getId() ) );

            if (master.getCalendar() == null) {
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", new Object[ 0 ] ) );
            } else {
                Object[] localDates = master.getCalendar().getBookedDates().keySet().toArray();
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", localDates ) );
            }

            statement.setString( 3,master.getFullName() );

            statement.setDouble( 4,master.getDailyPayment() );

            statement.setString( 5, String.valueOf( master.getSpeciality() ) );


            statement.executeUpdate();

        }
    }
}
