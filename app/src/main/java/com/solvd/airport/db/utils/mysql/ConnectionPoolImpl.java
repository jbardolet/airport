package com.solvd.airport.db.utils.mysql;

import com.solvd.airport.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger logger = LogManager.getLogger("ConnectionPoolImpl");
    private final static String URL="jdbc:mysql://localhost:3306/airport";
    private final static String USER="root";
    private final static String PASSWORD="";
    private static int size;

    private static int INITIAL_POOL_SIZE = 5;
    private static ConnectionPoolImpl instance;
    private static BlockingQueue<Connection> connections;


    public static synchronized ConnectionPoolImpl getInstance() {
        if(instance == null){
            instance = new ConnectionPoolImpl();
            connections = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        }
        return instance;
    }

    private synchronized Connection createNewConnection() throws SQLException {

            Connection connection= DriverManager.getConnection(URL, USER,PASSWORD);

            return connection;
    }


    @Override
    public synchronized Connection getConnection() throws SQLException, InterruptedException {
        long timestamp = System.currentTimeMillis();


        if(connections.size()==INITIAL_POOL_SIZE){
            this.wait(Math.max(timestamp-System.currentTimeMillis(),1));
            if(timestamp<= System.currentTimeMillis()){
                throw new SQLException("Connection not avaiable");
            }
        }else{
            connections.put(createNewConnection());
            logger.info(connections.size());
        }
        return connections.peek();
    }


    public synchronized void releaseConnection(Connection connection)  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info(connections.size());

    }

}
