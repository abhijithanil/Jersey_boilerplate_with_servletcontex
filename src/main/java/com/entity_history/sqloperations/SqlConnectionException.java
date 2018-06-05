package com.entity_history.sqloperations;

import org.apache.log4j.Logger;

public class SqlConnectionException extends Throwable {
    static final Logger logger = Logger.getLogger(SqlConnectionException.class);

    public SqlConnectionException(String message){
        logger.error("SQL-Connection Exception- reason:: "+message);
        System.exit(1);
    }
}
