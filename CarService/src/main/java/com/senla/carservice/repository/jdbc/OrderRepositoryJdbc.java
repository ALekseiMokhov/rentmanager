package com.senla.carservice.repository.jdbc;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.repository.IOrderRepository;
import dependency.injection.annotations.Qualifier;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

@Qualifier
public class OrderRepositoryJdbc implements IOrderRepository {
    private final static String FIND_SQL = "SELECT * FROM SENLA.ORDERS WHERE ID = ?";
    private final static String FIND_ALL_SQL = "SELECT * FROM SENLA.ORDERS";
    private final static String SAVE_SQL =
            "MERGE INTO SENLA.ORDERS (id,date_of_booking,beginning_date,finish_date,order_status,id_place) VALUES (?,?,?,?,?,?)";
    private final static String DELETE_SQL = "DELETE FROM SENLA.ORDERS WHERE ID = ?";
    private final static String INSERT_ORDERS_MASTERS_ID_SQL = "INSERT INTO SENLA.orders_masters (id_orders,id_masters) VALUES(?,?)";
    private final static String DELETE_ORDERS_MASTERS_ID_SQL = "DELETE FROM SENLA.orders_masters WHERE id_masters =?";


    private DataSourceFactory factory = DataSourceFactory.getInstance();

    @Override
    @SneakyThrows
    public Order findById(UUID id) {
        return null;
    }

    @Override
    @SneakyThrows
    public List <Order> findAll() {
        return null;
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

            statement.setObject( 2,  order.getDateBooked());

            statement.setObject( 3,  order.getStartOfExecution());

            statement.setObject( 4,  order.getFinishOfExecution());

            statement.setObject( 5,  order.getStatus());

            for (IMaster master : order.getMasters() ) {
                 manyToManyMap.setString( 1,String.valueOf( order.getId() ) );
                 manyToManyMap.setString( 2,String.valueOf(master.getId()) );
                 manyToManyMap.addBatch();
            }

            statement.executeUpdate();
            manyToManyMap.executeUpdate();

        }
    }
}
