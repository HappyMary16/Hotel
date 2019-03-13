package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.datasource.DataSource;
import ua.nure.borodin.hotel.model.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class Dao<T extends Entity> implements IDao<Integer, T> {
    private String type;
    private DataSource dataSource;

    public static final String SELECT_ALL = "SELECT * FROM %s";
    public static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";

    public Dao(Class<T> type) {
        this.type = type.getSimpleName().toLowerCase();
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<T> getAll() {
        String sql = String.format(SELECT_ALL, type.equals("actor") || type.equals("director") ? "person" : type);

        List<T> result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T getById(Integer id) {
        String sql = String.format(FIND_BY_ID, type.equals("actor") || type.equals("director") ? "person" : type);

        List<T> result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.get(0);
    }

    @Override
    public long insert(T entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity)) {
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

    @Override
    public void delete(Integer id) {
        String sql = String.format(DELETE_BY_ID, type);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = createUpdateStatement(connection, entity)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract List<T> readAll(ResultSet resultSet) throws SQLException;

    protected abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException;
}
