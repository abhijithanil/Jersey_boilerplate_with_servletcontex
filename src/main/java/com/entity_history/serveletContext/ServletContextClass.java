package com.entity_history.serveletContext;

import com.entity_history.sqloperations.MySqlConnectionCreator;
import com.entity_history.sqloperations.SqlConnectionException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletContextClass implements ServletContextListener {
    public static Connection connection;
    public Connection getConnection() throws SqlConnectionException {
        MySqlConnectionCreator mySqlConnectionCreator = MySqlConnectionCreator.getInstance();
        return mySqlConnectionCreator.getConnection();
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener started");
        try {
            connection=getConnection();
            servletContextEvent.getServletContext().setAttribute("connection",connection);
        } catch (SqlConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener destroyed");
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException se){
            // Do something
        }
    }
}
