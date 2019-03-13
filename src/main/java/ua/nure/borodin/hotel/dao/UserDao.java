package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;
import ua.nure.borodin.hotel.model.entity.Role;
import ua.nure.borodin.hotel.model.entity.User;

import java.sql.*;

public class UserDao {

    private static UserDao instance = new UserDao();

    private static DataSource dataSource = DataSource.getInstance();

    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?"+
                    "	WHERE id=?";

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID)) {

            UserMapper mapper = new UserMapper();
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = mapper.mapRow(rs);
            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) {
        User user = null;
        ResultSet rs;

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN)) {

            UserMapper mapper = new UserMapper();
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public void updateUser(User user) {
        try (Connection con = dataSource.getConnection()) {
            updateUser(con, user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getLastName());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }



    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));
                user.setRole(Role.getInstance(rs.getInt(Fields.USER__ROLE_ID)));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
