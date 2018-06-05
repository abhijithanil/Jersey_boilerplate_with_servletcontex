package com.entity_history.serveletContext;

import com.entity_history.sqloperations.MySqlConnectionCreator;
import com.entity_history.sqloperations.exception.SqlConnectionException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ServletContextClass implements ServletContextListener {
    public static Connection connection;
    public Connection getConnection() {
        MySqlConnectionCreator mySqlConnectionCreator = MySqlConnectionCreator.getInstance();
        return mySqlConnectionCreator.getConnection();
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener started");
        connection=getConnection();
        servletContextEvent.getServletContext().setAttribute("connection",connection);
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
