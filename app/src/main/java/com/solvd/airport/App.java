package com.solvd.airport;

import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    private static final Logger logger = LogManager.getLogger("App");

    public static void main(String[] args) throws SQLException, InterruptedException {
/*
        ConnectionUtils connectionUtils = new ConnectionUtils();
        Connection c = connectionUtils.getConn();

*/
        logger.info("------- Connection pool -------");


        for (int i = 1; i<=8;i++){
            Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            logger.info("connection"+ i);
            if(i ==4){
                ConnectionPoolImpl.getInstance().releaseConnection(connection);
            }
        }

/*
        CityDAO cityDAO = new CityDAO();
        logger.info(" City INSERT");
        City city = new City(3L,"City3", "Country3", "State3");
        cityDAO.insert(city);

        logger.info(" City SELECT all");
        ArrayList<City> cities = (ArrayList<City>) cityDAO.getAll();
        logger.info(cities);

        logger.info("DELETE by ID");
        cityDAO.deletePersonById(2L);
        logger.info(cityDAO.getAll());

        logger.info("Service Airline, list planes");
        AirlinesUtils airlinesUtils = new AirlinesUtils();
        logger.info(airlinesUtils.selelctByFirstAirline());

        */
    }
}
