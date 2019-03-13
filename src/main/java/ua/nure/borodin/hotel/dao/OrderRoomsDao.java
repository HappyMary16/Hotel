package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderRoomsDao {

    public static OrderRoomsDao instance = new OrderRoomsDao();

    private static DataSource dataSource = DataSource.getInstance();

    private static final String FIND_ALL_BY_ID = "SELECT * FROM order_room WHERE order_id = ?";

    private OrderRoomsDao() {
    }

    public static OrderRoomsDao getInstance() {
        return instance;
    }

    public List<Long> findAllByOrderId(Long orderId) {
        List<Long> result = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ID)) {

            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(resultSet.getLong("room_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
