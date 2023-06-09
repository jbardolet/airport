package com.solvd.hospital;

import com.solvd.hospital.db.dao.impl.CityDAO;
import com.solvd.hospital.db.dao.model.City;
import com.solvd.hospital.db.service.impl.AirlinesUtils;
import com.solvd.hospital.db.utils.mysql.ConnectionUtils;
import com.solvd.hospital.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    private static final Logger logger = LogManager.getLogger("App");

    public static void main(String[] args) throws SQLException, InterruptedException {

        ConnectionUtils connectionUtils = new ConnectionUtils();
        Connection c = connectionUtils.getConn();


        logger.info("------- Connection pool -------");


        for (int i = 1; i<=8;i++){
            Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            logger.info("connection"+ i);
            if(i ==4){
                ConnectionPoolImpl.getInstance().releaseConnection(connection);
            }
        }


        CityDAO cityDAO = new CityDAO();
        logger.info(" City INSERT");
        City city = new City(3L,"City3", "Country3", "State3");
        cityDAO.insert(city);

        logger.info(" City SELECT all");
        ArrayList<City> cities = (ArrayList<City>) cityDAO.getAll();
        logger.info(cities);

        logger.info("DELETE by ID");
        cityDAO.deleteByID(2L);
        logger.info(cityDAO.getAll());

        logger.info("Service Airline, list planes");
        AirlinesUtils airlinesUtils = new AirlinesUtils();
        logger.info(airlinesUtils.selelctByFirstAirline());

    }
}
