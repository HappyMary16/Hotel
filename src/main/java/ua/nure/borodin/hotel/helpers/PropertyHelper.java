package ua.nure.borodin.hotel.helpers;

import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

    private static PropertyHelper propertyHelper;
    private String jdbcURL;
    private String dbUserLogin;
    private String dbUserPassword;
    private String dbDriver;

    private PropertyHelper() {
        loadProperties();
    }

    public static synchronized PropertyHelper getInstance() {
        if (propertyHelper == null) {
            propertyHelper = new PropertyHelper();
        }
        return propertyHelper;
    }

    private void loadProperties() {
        Properties prop = new Properties();

        try {
            prop.load(PropertyHelper.class.getClassLoader().getResourceAsStream("database.properties"));

            this.dbDriver = prop.getProperty("dbDriver");
            this.jdbcURL = prop.getProperty("jdbcUrl");
            this.dbUserLogin = prop.getProperty("dbUserLogin");
            this.dbUserPassword = prop.getProperty("dbUserPassword");
        } catch (IOException e) {

        }
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public String getDbUserLogin() {
        return dbUserLogin;
    }

    public String getDbUserPassword() {
        return dbUserPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }
}
