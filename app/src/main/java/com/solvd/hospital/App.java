package com.solvd.hospital;

import com.solvd.hospital.db.mysql.ConnectionUtils;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
        ConnectionUtils connectionUtils = new ConnectionUtils();
        Connection c = connectionUtils.getConn();
    }
}
