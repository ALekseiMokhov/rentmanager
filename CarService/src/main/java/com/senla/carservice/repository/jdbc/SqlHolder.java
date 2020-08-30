package com.senla.carservice.repository.jdbc;

public interface SqlHolder {

    String FIND_PLACE_SQL = "SELECT * FROM SENLA.PLACE WHERE ID = ?";
    String FIND_ALL_PLACES_SQL = "SELECT * FROM SENLA.PLACE";
    String SAVE_PLACE_SQL = "MERGE INTO SENLA.PLACE (id,calendar) VALUES (?,?)";
    String DELETE_PLACE_SQL = "DELETE FROM SENLA.PLACE WHERE ID = ?";

    String FIND_MASTER_SQL = "SELECT * FROM SENLA.MASTER WHERE ID = ?";
    String FIND_ALL_MASTERS_SQL = "SELECT * FROM SENLA.MASTER";
    String SAVE_MASTER_SQL = "MERGE INTO SENLA.MASTER (id,calendar,fullname,dailypayment,speciality) VALUES (?,?,?,?,?)";
    String DELETE_MASTER_SQL = "DELETE FROM SENLA.MASTER WHERE ID = ?";

    String FIND_ORDER_SQL = "SELECT date_of_booking,beginning_date,finish_date,order_status," +
            " calendar FROM SENLA.orders o JOIN SENLA.place p ON o.id_place = p.id WHERE o.id = ?;";
    String FIND_ALL_ORDERS_SQL = "SELECT o.id,date_of_booking,beginning_date,finish_date,order_status, " +
            "calendar FROM SENLA.orders o JOIN SENLA.place p ON o.id_place = p.id;";
    String SAVE_ORDER_SQL =
            "MERGE INTO SENLA.ORDERS (id,date_of_booking,beginning_date,finish_date,order_status,id_place) VALUES (?,?,?,?,?,?)";
    String DELETE_ORDER_SQL = "DELETE FROM SENLA.ORDERS WHERE ID = ?";

    String INSERT_ORDERS_MASTERS_ID_SQL = "INSERT INTO SENLA.orders_masters (id_orders,id_masters) VALUES(?,?)";
    String SELECT_MASTERS_BY_ID_SQL = "SELECT m.id,calendar,fullname,dailypayment,speciality" +
            " FROM SENLA.master m JOIN SENLA.orders_masters o_m ON m.id = o_m.id_masters JOIN SENLA.orders o " +
            "ON o.id = o_m.id_orders WHERE o.id = ?";
    String DELETE_ORDERS_MASTERS_ID_SQL = "DELETE FROM SENLA.orders_masters WHERE id_masters =?";

}
