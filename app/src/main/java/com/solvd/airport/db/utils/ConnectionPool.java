package com.solvd.airport.db.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection() throws SQLException, InterruptedException;
    void releaseConnection(Connection connection) throws SQLException;

}
