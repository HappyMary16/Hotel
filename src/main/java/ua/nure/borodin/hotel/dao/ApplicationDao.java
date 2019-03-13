package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;
import ua.nure.borodin.hotel.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for menu related entities.
 */
public class ApplicationDao {

    private static DataSource dataSource = DataSource.getInstance();

    private static ApplicationDao instance = new ApplicationDao();

    private static final String SQL__FIND_ALL_APPLICATIONS =
            "SELECT * FROM applications";

    private static final String SQL_FIND_APPLICATION_BY_STATUS =
            "SELECT * FROM applications WHERE status_id = ?";

    private static final String SQL_INSERT_ORDER =
            "INSERT INTO applications VALUES (default, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_ORDER =
            "UPDATE applications SET status_id=? WHERE id=?";

    private ApplicationDao() {
    }

    public static ApplicationDao getInstance() {
        return instance;
    }

    public long insert(Application entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.setLong(2, entity.getNumberPlaces());
            preparedStatement.setInt(3, entity.getCategory().getId());
            preparedStatement.setInt(4, entity.getStatus().getId());
            preparedStatement.setDate(5, new java.sql.Date(entity.getFrom().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(entity.getTo().getTime()));

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getId();
    }

    public void update(Long appId, ApplicationStatus status) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);

            preparedStatement.setInt(1, status.getId());
            preparedStatement.setLong(2, appId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all applications.
     *
     * @return List of applications.
     */
    public List<Application> findApplications() {

        List<Application> applications = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs =stmt.executeQuery(SQL__FIND_ALL_APPLICATIONS)) {

            ApplicationMapper mapper = new ApplicationMapper();
            while (rs.next()) {
                applications.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return applications;
    }

    /**
     * Returns menu items of the given order.
     *
     * @param status Application status.
     * @return List of menu item entities.
     */
    public List<Application> findApplications(ApplicationStatus status) {
        List<Application> applications = new ArrayList<>();

        ResultSet rs;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_FIND_APPLICATION_BY_STATUS)) {

            ApplicationMapper mapper = new ApplicationMapper();
            pstmt.setLong(1, status.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                applications.add(mapper.mapRow(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return applications;
    }

    /**
     * Returns menu items with given identifiers.
     *
     * @param ids Identifiers of menu items.
     * @return List of menu item entities.
     */
    public List<Application> findApplications(List<String> ids) {
        List<Application> applications = new ArrayList<>();

        ResultSet rs = null;
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {

            ApplicationMapper mapper = new ApplicationMapper();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM applications WHERE id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                applications.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return applications;
    }

    /**
     * Extracts a menu item from the result set row.
     */
    private static class ApplicationMapper implements EntityMapper<Application> {

        @Override
        public Application mapRow(ResultSet rs) {
            try {
                Application application = new Application();
                application.setId(rs.getLong(Fields.ENTITY__ID));
                application.setUserId(rs.getLong(Fields.ORDER__USER_ID));
                application.setNumberPlaces(rs.getInt(Fields.ROOM_NUMBER_PLACE));
                application.setCategory(Category.getInstance(rs.getInt(Fields.ROOM_CATEGORY_ID)));
                application.setStatus(ApplicationStatus.getInstance(rs.getInt(Fields.ORDER__STATUS_ID)));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    application.setFrom(dateFormat.parse(rs
                            .getDate(Fields.ORDER__START_ORDER_DATE).toString()));
                    application.setTo(dateFormat.parse(rs
                            .getDate(Fields.ORDER__END_ORDER_DATE).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return application;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
