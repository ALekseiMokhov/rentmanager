package com.senla.carservice.repository.jdbc;

import com.senla.carservice.calendar.Calendar;
import com.senla.carservice.entity.master.*;
import com.senla.carservice.repository.IMasterRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.senla.carservice.repository.jdbc.SqlHolder.*;


public class MasterRepositoryJdbc implements IMasterRepository {

    private DataSourceFactory factory = DataSourceFactory.getInstance();

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    @SneakyThrows
    public IMaster findById(UUID id) {
        IMaster master = null;
        try {
            connection = factory.getDatasource().getConnection();
            connection.setAutoCommit( false );
            statement = connection.prepareStatement( FIND_MASTER_SQL );

            statement.setString( 1, String.valueOf( id ) );
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Speciality speciality = Speciality.valueOf( resultSet.getString( "speciality" ) );
                switch (speciality) {
                    case RESHAPER -> master = new Reshaper();
                    case PAINTER -> master = new Painter();
                    case MECHANIC -> master = new Mechanic();
                    case ELECTRICIAN -> master = new Electrician();
                    default -> throw new IllegalStateException( "Wrong speciality!" );
                }
                ((AbstractMaster) master).setSpeciality( speciality );

                ((AbstractMaster) master).setId( id );

                String fullName = resultSet.getString( "fullname" );
                ((AbstractMaster) master).setFullName( fullName );

                Double dailyPayment = resultSet.getDouble( "dailypayment" );
                ((AbstractMaster) master).setDailyPayment( dailyPayment );

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();
                for (Object o : array) {
                    if (master.getCalendar() == null) {
                        master.setCalendar( new Calendar() );
                    }
                    master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
            }
            connection.commit();
            connection.close();
        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
        return master;
    }

    @Override
    @SneakyThrows
    public List <IMaster> findAll() {
        List <IMaster> result = new ArrayList <>();
        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( FIND_ALL_MASTERS_SQL );
            connection.setAutoCommit( false );
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                IMaster master = null;
                Speciality speciality = Speciality.valueOf( resultSet.getString( "speciality" ) );
                switch (speciality) {
                    case RESHAPER -> master = new Reshaper();
                    case PAINTER -> master = new Painter();
                    case MECHANIC -> master = new Mechanic();
                    case ELECTRICIAN -> master = new Electrician();
                    default -> throw new IllegalStateException( "Wrong speciality!" );
                }
                ((AbstractMaster) master).setSpeciality( speciality );

                UUID id = UUID.fromString( resultSet.getString( "id" ) );
                ((AbstractMaster) master).setId( id );

                String fullName = resultSet.getString( "fullname" );
                ((AbstractMaster) master).setFullName( fullName );

                Double dailyPayment = resultSet.getDouble( "dailypayment" );
                ((AbstractMaster) master).setDailyPayment( dailyPayment );

                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();
                master.setCalendar( new Calendar() );
                for (Object o : array) {
                    master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                result.add( master );
            }
            connection.commit();
            connection.close();
        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }

        return result;
    }

    @Override
    @SneakyThrows
    public void delete(UUID id) {
        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( DELETE_MASTER_SQL );

            statement.setString( 1, String.valueOf( id ) );
            statement.executeUpdate();
        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
    }

    @Override
    @SneakyThrows
    public void save(IMaster master) {
        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( SAVE_MASTER_SQL );
            statement.setString( 1, String.valueOf( master.getId() ) );

            if (master.getCalendar() == null) {
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", new Object[ 0 ] ) );
            } else {
                Object[] localDates = master.getCalendar().getBookedDates().keySet().toArray();
                statement.setArray( 2, connection.createArrayOf( "TIMESTAMP", localDates ) );
            }

            statement.setString( 3, master.getFullName() );

            statement.setDouble( 4, master.getDailyPayment() );

            statement.setString( 5, String.valueOf( master.getSpeciality() ) );


            statement.executeUpdate();

        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
    }
}
