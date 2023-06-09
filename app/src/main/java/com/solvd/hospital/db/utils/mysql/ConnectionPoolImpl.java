package com.solvd.hospital.db.utils.mysql;

import com.solvd.hospital.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger logger = LogManager.getLogger("ConnectionPoolImpl");
    private final static String URL="jdbc:mysql://localhost:3306/airport";
    private final static String USER="root";
    private final static String PASSWORD="";
    private static int size;

    private static int INITIAL_POOL_SIZE = 5;
    private static ConnectionPoolImpl instance;
    private static Queue<Connection> connections;

    public static synchronized ConnectionPoolImpl getInstance() {
        if(instance == null){
            instance = new ConnectionPoolImpl();
            size=0;
            connections = new LinkedList<>();
        }
        return instance;
    }

    private synchronized Connection createNewConnection() throws SQLException {

            Connection connection= DriverManager.getConnection(URL, USER,PASSWORD);

            return connection;
    }


    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        long timestamp = System.currentTimeMillis();
        boolean createNewConnection = false;

        synchronized (this) {
            while (connections.isEmpty()) {
                if (size < INITIAL_POOL_SIZE) {
                    size++;
                  //  logger.info("ac size "+size);
                    createNewConnection = true;
                    break;
                } else {
                    this.wait(Math.max(timestamp - System.currentTimeMillis(), 1));

                    if (timestamp <= System.currentTimeMillis()) {
                        throw new SQLException("Connection not available");
                    }
                }
            }

            if (!createNewConnection) {
                return connections.poll();
            }
        }

        return createNewConnection();
    }


    public synchronized void releaseConnection(Connection connection) throws SQLException {
        connections.offer(connection);
        size--;
       // logger.info("ac size "+size);
    }

}
