package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.*;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.repository.IOrderRepository;
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


public class OrderRepositoryJdbc implements IOrderRepository {


    private DataSourceFactory factory = DataSourceFactory.getInstance();

    private Connection connection;
    private PreparedStatement statement;
    private PreparedStatement mastersMapStatement;
    private ResultSet resultSet;
    private ResultSet resultSetSubQuery;

    @Override
    @SneakyThrows
    public Order findById(UUID id) {
        Order order = new Order();
        order.setId( id );
        try {
            connection = factory.getDatasource().getConnection();
            mastersMapStatement = connection.prepareStatement( SELECT_MASTERS_BY_ID_SQL );
            statement = connection.prepareStatement( FIND_ORDER_SQL );

            statement.setString( 1, String.valueOf( id ) );
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order.setDateBooked( LocalDate.parse( resultSet.getString( "date_of_booking" ) ) );

                order.setStartOfExecution(
                        resultSet.getString( "beginning_date" ) == null ? null
                                : LocalDate.parse( resultSet.getString( "beginning_date" ) ) );

                order.setFinishOfExecution(
                        resultSet.getString( "finish_date" ) == null ? null
                                : LocalDate.parse( resultSet.getString( "finish_date" ) ) );

                order.setStatus( OrderStatus.valueOf( resultSet.getString( "order_status" ) ) );
                /*Setting a place for Order*/
                Place place = new Place( new Calendar() );
                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();

                for (Object o : array) {
                    place.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                order.setPlace( place );
            }
            /*Fetching related masters*/
            mastersMapStatement.setString( 1, String.valueOf( id ) );
            resultSetSubQuery = mastersMapStatement.executeQuery();
            List <IMaster> masters = new ArrayList <>();
            while (resultSetSubQuery.next()) {
                AbstractMaster master = null;
                Speciality speciality = Speciality.valueOf( resultSetSubQuery.getString( "speciality" ) );
                switch (speciality) {
                    case RESHAPER -> master = new Reshaper();
                    case PAINTER -> master = new Painter();
                    case MECHANIC -> master = new Mechanic();
                    case ELECTRICIAN -> master = new Electrician();
                    default -> throw new IllegalStateException( "Wrong speciality!" );
                }
                master.setSpeciality( speciality );

                UUID masterId = UUID.fromString( resultSetSubQuery.getString( "id" ) );
                master.setId( masterId );

                String fullName = resultSetSubQuery.getString( "fullname" );
                master.setFullName( fullName );

                Double dailyPayment = resultSetSubQuery.getDouble( "dailypayment" );
                master.setDailyPayment( dailyPayment );

                Object[] array = (Object[]) resultSetSubQuery.getArray( "calendar" ).getArray();
                for (Object o : array) {
                    if (master.getCalendar() == null) {
                        master.setCalendar( new Calendar() );
                    }
                    master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                masters.add( master );
            }
            order.setMasters( masters );
        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
        return order;
    }

    @Override
    @SneakyThrows
    public List <Order> findAll() {
        List <Order> result = new ArrayList <>();
        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( FIND_ALL_ORDERS_SQL );
            mastersMapStatement = connection.prepareStatement( SELECT_MASTERS_BY_ID_SQL );
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();

                UUID id = UUID.fromString( resultSet.getString( "id" ) );

                order.setId( id );

                order.setDateBooked( LocalDate.parse( resultSet.getString( "date_of_booking" ) ) );

                order.setStartOfExecution(
                        resultSet.getString( "beginning_date" ) == null ? null
                                : LocalDate.parse( resultSet.getString( "beginning_date" ) ) );

                order.setFinishOfExecution(
                        resultSet.getString( "finish_date" ) == null ? null
                                : LocalDate.parse( resultSet.getString( "finish_date" ) ) );

                order.setStatus( OrderStatus.valueOf( resultSet.getString( "order_status" ) ) );
                /*Setting a place for Order*/
                Place place = new Place( new Calendar() );
                Object[] array = (Object[]) resultSet.getArray( "calendar" ).getArray();

                for (Object o : array) {
                    place.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                }
                order.setPlace( place );

                /*Fetching related masters*/
                mastersMapStatement.setString( 1, String.valueOf( id ) );
                ResultSet resultSetSubQuery = mastersMapStatement.executeQuery();
                List <IMaster> masters = new ArrayList <>();
                while (resultSetSubQuery.next()) {
                    AbstractMaster master = null;
                    Speciality speciality = Speciality.valueOf( resultSetSubQuery.getString( "speciality" ) );
                    switch (speciality) {
                        case RESHAPER -> master = new Reshaper();
                        case PAINTER -> master = new Painter();
                        case MECHANIC -> master = new Mechanic();
                        case ELECTRICIAN -> master = new Electrician();
                        default -> throw new IllegalStateException( "Wrong speciality!" );
                    }
                    master.setSpeciality( speciality );

                    UUID masterId = UUID.fromString( resultSetSubQuery.getString( "id" ) );
                    master.setId( masterId );

                    String fullName = resultSetSubQuery.getString( "fullname" );
                    master.setFullName( fullName );

                    Double dailyPayment = resultSetSubQuery.getDouble( "dailypayment" );
                    master.setDailyPayment( dailyPayment );

                    Object[] arr = (Object[]) resultSetSubQuery.getArray( "calendar" ).getArray();
                    for (Object o : arr) {
                        if (master.getCalendar() == null) {
                            master.setCalendar( new Calendar() );
                        }
                        master.getCalendar().setDateForBooking( LocalDate.parse( o.toString() ) );
                    }
                    masters.add( master );
                }
                order.setMasters( masters );

                result.add( order );
            }
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
            statement = connection.prepareStatement( DELETE_ORDER_SQL );
            mastersMapStatement = connection.prepareStatement( DELETE_ORDERS_MASTERS_ID_SQL );

            statement.setString( 1, String.valueOf( id ) );
            mastersMapStatement.setString( 1, String.valueOf( id ) );
            statement.executeUpdate();
            mastersMapStatement.executeUpdate();
        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
    }

    @Override
    @SneakyThrows
    public void save(Order order) {
        try {
            connection = factory.getDatasource().getConnection();
            statement = connection.prepareStatement( SAVE_ORDER_SQL );
            mastersMapStatement = connection.prepareStatement( INSERT_ORDERS_MASTERS_ID_SQL );
            statement.setString( 1, String.valueOf( order.getId() ) );

            statement.setObject( 2, order.getDateBooked() );

            statement.setObject( 3, order.getStartOfExecution() );

            statement.setObject( 4, order.getFinishOfExecution() );

            statement.setObject( 5, String.valueOf( order.getStatus() ) );

            statement.setObject( 6, order.getPlace().getId() );

            for (IMaster master : order.getMasters()) {
                mastersMapStatement.setString( 1, String.valueOf( order.getId() ) );
                mastersMapStatement.setString( 2, String.valueOf( master.getId() ) );
                mastersMapStatement.addBatch();
            }

            statement.executeUpdate();
            mastersMapStatement.executeUpdate();

        } catch (
                SQLException e) {
            connection.rollback();
            connection.close();
            throw e;
        }
    }
}
