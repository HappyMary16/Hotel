package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;
import ua.nure.borodin.hotel.model.entity.Category;
import ua.nure.borodin.hotel.model.entity.Order;
import ua.nure.borodin.hotel.model.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data access object for menu related entities.
 */
public class RoomsDao {

    private static RoomsDao instance = new RoomsDao();

    private static final DataSource dataSource = DataSource.getInstance();

    private static final String SQL__FIND_ALL_ROOMS =
            "SELECT * FROM rooms";

    private static final String SQL__FIND_MENU_ITEMS_BY_ORDER =
            "SELECT * FROM rooms WHERE id IN (SELECT menu_id FROM orders_menu WHERE order_id=?)";

    private static final String SQL_FIND_ALL_CATEGORIES =
            "SELECT * FROM categories";

    private static final String SQL__FIND_AVAILABLE_ROOM =
            "SELECT * FROM rooms WHERE id NOT IN " +
                    "(SELECT room_id FROM orders INNER JOIN order_room ON orders.id = order_room.order_id " +
                    "WHERE orders.status_id != 3 " +
                    "AND ((start_order <= ? AND end_order >= ?) " +
                    "OR (start_order <= ? AND end_order >= ?)" +
                    "OR (start_order >= ? AND end_order <= ?)))";

    private RoomsDao() {
    }

    public static RoomsDao getInstance() {
        return instance;
    }

    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<Category> findCategories() {

        List<Category> categoriesList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_CATEGORIES)) {

            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                categoriesList.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categoriesList;
    }

    /**
     * Returns all menu items.
     *
     * @return List of menu item entities.
     */
    public List<Room> findRooms() {
        List<Room> RoomsList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL__FIND_ALL_ROOMS)) {

            RoomMapper mapper = new RoomMapper();
            while (rs.next()) {
                RoomsList.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return RoomsList;
    }

    /**
     * Returns menu items of the given order.
     *
     * @param order Order entity.
     * @return List of menu item entities.
     */
    public List<Room> findRooms(Order order) {
        List<Room> RoomsList = new ArrayList<>();
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_MENU_ITEMS_BY_ORDER)) {

            RoomMapper mapper = new RoomMapper();
            pstmt.setLong(1, order.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomsList.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return RoomsList;
    }

    public List<Room> findAvailableRooms(Date from, Date to) {
        List<Room> rooms = new ArrayList<>();
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_AVAILABLE_ROOM)) {

            RoomMapper mapper = new RoomMapper();

            pstmt.setDate(1, new java.sql.Date(from.getTime()));
            pstmt.setDate(2, new java.sql.Date(from.getTime()));
            pstmt.setDate(3, new java.sql.Date(to.getTime()));
            pstmt.setDate(4, new java.sql.Date(to.getTime()));
            pstmt.setDate(5, new java.sql.Date(from.getTime()));
            pstmt.setDate(6, new java.sql.Date(to.getTime()));

            rs = pstmt.executeQuery();
            while (rs.next()) {
                rooms.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rooms;
    }

    /**
     * Returns menu items with given identifiers.
     *
     * @param ids Identifiers of menu items.
     * @return List of menu item entities.
     */
    public List<Room> findRooms(String[] ids) {
        List<Room> RoomsList = new ArrayList<>();
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();) {

            RoomMapper mapper = new RoomMapper();

            StringBuilder query = new StringBuilder(
                    "SELECT * FROM rooms WHERE id IN (");
            for (String idAsString : ids)
                query.append(idAsString).append(',');
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                RoomsList.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return RoomsList;
    }

    /**
     * Extracts a category from the result set row.
     */
    private static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            try {
                Category category = Category.getInstance(rs.getInt(Fields.ENTITY__ID));
                return category;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a menu item from the result set row.
     */
    private static class RoomMapper implements EntityMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs) {
            try {
                Room room = new Room();
                room.setId(rs.getLong(Fields.ENTITY__ID));
                room.setNumberPlace(rs.getInt(Fields.ROOM_NUMBER_PLACE));
                room.setPrice(rs.getInt(Fields.ROOM_PRICE));
                room.setCategory(Category.getInstance(rs.getInt(Fields.ROOM_CATEGORY_ID)));
                return room;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
