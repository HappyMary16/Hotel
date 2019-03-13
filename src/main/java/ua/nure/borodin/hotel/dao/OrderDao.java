package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;
import ua.nure.borodin.hotel.model.bean.UserOrderBean;
import ua.nure.borodin.hotel.model.entity.Order;
import ua.nure.borodin.hotel.model.entity.OrderStatus;
import ua.nure.borodin.hotel.model.entity.Room;
import ua.nure.borodin.hotel.model.entity.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Data access object for Order entity and UserOrderBean bean.
 */
public class OrderDao {

    private static OrderDao instance = new OrderDao();

    private static DataSource dataSource = DataSource.getInstance();

    private static final String SQL__GET_USER_ORDER_BEANS =
            "SELECT o.id, u.first_name, u.last_name, o.bill, s.name" +
                    "	FROM users AS u, orders AS o, order_statuses AS s" +
                    "	WHERE o.user_id=u.id AND o.status_id=s.id";

    private static final String SQL__FIND_ALL_ORDERS =
            "SELECT * FROM orders";

    private static final String SQL__FIND_ORDERS_BY_USER =
            "SELECT * FROM orders WHERE user_id=?";

    private static final String SQL__FIND_ORDERS_BY_STATUS =
            "SELECT * FROM orders WHERE status_id=?";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO orders " +
                    "(bill, user_id, status_id, start_order, end_order)" +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_ORDER_ROOMS =
            "INSERT INTO order_room VALUES(?, ?)";

    private static final String SQL_UPDATE_ORDER =
            "UPDATE orders SET status_id = ?, set_bill = ? WHERE id = ?";

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return instance;
    }

    public long insert(Order entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementRooms = connection.prepareStatement(SQL_INSERT_ORDER_ROOMS)) {

            preparedStatement.setInt(1, entity.getBill());
            preparedStatement.setLong(2, entity.getUserId());
            preparedStatement.setInt(3, entity.getStatus().getId());
            preparedStatement.setDate(4, new java.sql.Date(entity.getFrom().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(entity.getTo().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }

            preparedStatementRooms.setLong(1, entity.getId());

            for (Long room :
                    entity.getRoomsId()) {
                preparedStatementRooms.setLong(2, room);
                preparedStatementRooms.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getId();
    }

    public void update(Long orderId, Date setBill, OrderStatus status) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);

            preparedStatement.setInt(1, status.getId());
            preparedStatement.setDate(2, new java.sql.Date(setBill.getTime()));
            preparedStatement.setLong(3, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<UserOrderBean> getUserOrderBeans() {
        List<UserOrderBean> orderUserBeanList = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        try (Connection con = dataSource.getConnection()) {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__GET_USER_ORDER_BEANS);
            UserOrderBeanMapper mapper = new UserOrderBeanMapper();
            while (rs.next()) {
                orderUserBeanList.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return orderUserBeanList;
    }

    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() {
        List<Order> ordersList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL__FIND_ALL_ORDERS)) {

            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                ordersList.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ordersList;
    }

    /**
     * Returns orders with the given status.
     *
     * @param statusId OrderStatus identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(int statusId) {
        List<Order> ordersList = new ArrayList<>();
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_ORDERS_BY_STATUS)) {

            OrderMapper mapper = new OrderMapper();
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return ordersList;
    }

    /**
     * Returns order with given identifier.
     *
     * @param id Orders identifier.
     * @return Order entity.
     */
    public Order findOrder(long id) {
        List<Order> orders = findOrders(Arrays.asList(String.valueOf(id)));
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    /**
     * Returns orders with given identifiers.
     *
     * @param ids Orders identifiers.
     * @return List of order entities.
     */
    public List<Order> findOrders(List<String> ids) {
        List<Order> ordersList = new ArrayList<>();

        ResultSet rs;
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {

            OrderMapper mapper = new OrderMapper();
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM orders WHERE id IN (");
            for (String idAsString : ids)
                query.append(idAsString).append(',');
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                ordersList.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ordersList;
    }

    /**
     * Returns orders of the given user and status
     *
     * @param user User entity.
     * @return List of order entities.
     */
    public List<Order> findOrders(User user) {
        List<Order> ordersList = new ArrayList<>();
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_ORDERS_BY_USER)) {

            OrderMapper mapper = new OrderMapper();
            pstmt.setLong(1, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ordersList;
    }

    /**
     * Extracts a user order bean from the result set row.
     */
    private static class UserOrderBeanMapper implements EntityMapper<UserOrderBean> {

        @Override
        public UserOrderBean mapRow(ResultSet rs) {
            try {
                UserOrderBean bean = new UserOrderBean();
                bean.setId(rs.getLong(Fields.USER_ORDER_BEAN__ORDER_ID));
                bean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN__ORDER_BILL));
                bean.setFirstName(rs.getString(Fields.USER_ORDER_BEAN__USER_FIRST_NAME));
                bean.setLastName(rs.getString(Fields.USER_ORDER_BEAN__USER_LAST_NAME));
                bean.setStatusName(rs.getString(Fields.USER_ORDER_BEAN__STATUS_NAME));
                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts order from the result set row.
     */
    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) {
            try {
                Order order = new Order();
                order.setId(rs.getLong(Fields.ENTITY__ID));
                order.setBill(rs.getInt(Fields.ORDER__BILL));
                order.setUserId(rs.getLong(Fields.ORDER__USER_ID));
                order.setRoomsId(DaoFactory.getInstance().getOrderRoomsDao().findAllByOrderId(order.getId()));
                order.setStatus(OrderStatus.getInstance(rs.getInt(Fields.ORDER__STATUS_ID)));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    if (rs.getDate(Fields.ORDER__SET_BILL_DATE) != null) {
                        order.setSetBill(dateFormat.parse(rs
                                .getDate(Fields.ORDER__SET_BILL_DATE).toString()));
                    }
                    order.setFrom(dateFormat.parse(rs
                            .getDate(Fields.ORDER__START_ORDER_DATE).toString()));
                    order.setTo(dateFormat.parse(rs
                            .getDate(Fields.ORDER__END_ORDER_DATE).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return order;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
