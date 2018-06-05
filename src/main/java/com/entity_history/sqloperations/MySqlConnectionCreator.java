package com.entity_history.sqloperations;

import com.entity_history.configurationloader.ConfigurationLoader;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.resource.Singleton;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Singleton
public class MySqlConnectionCreator {
    static final Logger logger = Logger.getLogger(MySqlConnectionCreator.class);
    private final Properties prop = ConfigurationLoader.getInstance().getProperties();
    public static MySqlConnectionCreator connector = new MySqlConnectionCreator();
    private Connection connection;
    private String connectionString = prop.getProperty("connectionString");
    private String pass = prop.getProperty("password");
    private String userName = prop.getProperty("userName");
    private String jdbcDriver = prop.getProperty("driver");

    private MySqlConnectionCreator() {
        try {
            connection = createConnetion();
        } catch (SqlConnectionException e) {

        }
    }

    public static MySqlConnectionCreator getInstance(){
        return connector;
    }

    public Connection createConnetion() throws SqlConnectionException {
        try {
            String connectionURL = connectionString;
            Class.forName(jdbcDriver).newInstance();
            Connection connection = DriverManager.getConnection(connectionURL, userName, pass);
            logger.info("MySqlConnectionCretor::createConnection: sql connection created");
            System.out.println("connection success");
            return connection;
        } catch (SQLException e) {
            throw new SqlConnectionException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlConnectionException(e.getMessage());
        }
    }

    public  Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            return (Connection) new SqlConnectionException("Broken Connection");
        }
    }
}
