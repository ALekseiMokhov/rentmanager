package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.*;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.repository.IOrderRepository;
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
public class OrderRepositoryJdbc implements IOrderRepository {
    private final static String FIND_SQL = "SELECT date_of_booking,beginning_date,finish_date,order_status," +
            " calendar FROM SENLA.orders o JOIN SENLA.place p ON o.id_place = p.id WHERE o.id = ?;";
    private final static String FIND_ALL_SQL = "SELECT o.id,date_of_booking,beginning_date,finish_date,order_status, " +
            "calendar FROM SENLA.orders o JOIN SENLA.place p ON o.id_place = p.id;";
    private final static String SAVE_SQL =
            "MERGE INTO SENLA.ORDERS (id,date_of_booking,beginning_date,finish_date,order_status,id_place) VALUES (?,?,?,?,?,?)";
    private final static String DELETE_SQL = "DELETE FROM SENLA.ORDERS WHERE ID = ?";

    private final static String INSERT_ORDERS_MASTERS_ID_SQL = "INSERT INTO SENLA.orders_masters (id_orders,id_masters) VALUES(?,?)";
    private final static String SELECT_MASTERS_BY_ID_SQL = "SELECT m.id,calendar,fullname,dailypayment,speciality" +
            " FROM SENLA.master m JOIN SENLA.orders_masters o_m ON m.id = o_m.id_masters JOIN SENLA.orders o " +
            "ON o.id = o_m.id_orders WHERE o.id = ?";
    private final static String UPDATE_ORDERS_MASTERS_ID_SQL = "UPDATE SENLA.orders_masters SET id_masters =? WHERE id_orders = ?";
    private final static String DELETE_ORDERS_MASTERS_ID_SQL = "DELETE FROM SENLA.orders_masters WHERE id_masters =?";


    private DataSourceFactory factory = DataSourceFactory.getInstance();


    @Override
    @SneakyThrows
    public Order findById(UUID id) {
        Order order = new Order();
        order.setId( id );
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_SQL );
             PreparedStatement mastersMapStatement = connection.prepareStatement( SELECT_MASTERS_BY_ID_SQL );
        ) {

            statement.setString( 1, String.valueOf( id ) );
            ResultSet resultSet = statement.executeQuery();

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
        }

        return order;
    }

    @Override
    @SneakyThrows
    public List <Order> findAll() {
        List<Order>result = new ArrayList <>();
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( FIND_ALL_SQL );
             PreparedStatement mastersMapStatement = connection.prepareStatement( SELECT_MASTERS_BY_ID_SQL );
             ResultSet resultSet = statement.executeQuery();
        ) {
           while (resultSet.next())  {
               Order order = new Order();

               UUID id = UUID.fromString( resultSet.getString( "id" ) ) ;

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
               
               result.add( order ) ;
           }
        }
        return result;
    }

    @Override
    @SneakyThrows
    public void delete(UUID id) {

    }

    @Override
    @SneakyThrows
    public void save(Order order) {
        try (Connection connection = factory.getDatasource().getConnection();
             PreparedStatement statement = connection.prepareStatement( SAVE_SQL );
             PreparedStatement manyToManyMap = connection.prepareStatement( INSERT_ORDERS_MASTERS_ID_SQL );
        ) {
            statement.setString( 1, String.valueOf( order.getId() ) );

            statement.setObject( 2, order.getDateBooked() );

            statement.setObject( 3, order.getStartOfExecution() );

            statement.setObject( 4, order.getFinishOfExecution() );

            statement.setObject( 5, String.valueOf( order.getStatus() ) );

            statement.setObject( 6, order.getPlace().getId() );

            for (IMaster master : order.getMasters()) {
                manyToManyMap.setString( 1, String.valueOf( order.getId() ) );
                manyToManyMap.setString( 2, String.valueOf( master.getId() ) );
                manyToManyMap.addBatch();
            }

            statement.executeUpdate();
            manyToManyMap.executeUpdate();

        }
    }
}
