package com.solvd.hospital.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    Connection conn;

    public Connection getConn(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport", "root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }


}
