package com.solvd.airport.parserXML;


import com.solvd.airport.db.dao.model.Trip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UnMarshaling {

    private static final Logger logger = LogManager.getLogger("UnMarshaling");
    private static final String FILENAME_CITY = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "resources"+File.separator+"singleCity.xml";

    private static final String FILENAME = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "resources"+File.separator+"airport.xml";

    public static void main(String[] args) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Trip.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();


        Trip trip = (Trip) jaxbUnmarshaller.unmarshal( new File(FILENAME) );
        logger.info(trip.getId());
        logger.info(trip.getAirplane().getGate().getId());
        logger.info(trip.getAirplane().getAirline().getName());
        logger.info(trip.getCityFrom());
        logger.info(trip.getCityTo());
        logger.info(trip.getDepartureDate());
        //logger.info(trip.getTickets().get(0).getId());

 /*       JAXBContext jaxbContext = JAXBContext.newInstance(Ticket.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Ticket ticket = (Ticket) jaxbUnmarshaller.unmarshal(new File(FILENAME));
        logger.info(ticket.getId());
        logger.info(ticket.getLuggage().getId());
        logger.info(ticket.getFood().getDescription());
        logger.info(ticket.getSeat());
        logger.info(ticket.getPerson().getCity());
*/

    }
}
