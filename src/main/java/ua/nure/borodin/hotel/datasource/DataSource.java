package ua.nure.borodin.hotel.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ua.nure.borodin.hotel.helpers.PropertyHelper;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static ComboPooledDataSource poolConnections;
    private static DataSource dataSource;

    private DataSource() {
        initPoolConnections();
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource ;
    }

    private void initPoolConnections() {
        poolConnections = new ComboPooledDataSource();
        PropertyHelper propertyHelper = PropertyHelper.getInstance();

        try {
            poolConnections.setDriverClass(propertyHelper.getDbDriver());
            poolConnections.setJdbcUrl(propertyHelper.getJdbcURL());
            poolConnections.setUser(propertyHelper.getDbUserLogin());
            poolConnections.setPassword(propertyHelper.getDbUserPassword());

            poolConnections.setMinPoolSize(1);
            poolConnections.setAcquireIncrement(1);
            poolConnections.setMinPoolSize(5);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = poolConnections.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
