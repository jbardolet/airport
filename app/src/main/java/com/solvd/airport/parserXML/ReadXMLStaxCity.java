package com.solvd.airport.parserXML;

import com.solvd.airport.db.dao.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLStaxCity {
    private static final Logger logger = LogManager.getLogger("ReadXMLStax");
    private static final String FILENAME = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "resources"+File.separator+"city.xml";

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        List<City> cityList = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        Path path = Paths.get(FILENAME);
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path.toFile()));

        logger.info(reader);
        System.out.println(reader);
        City city = new City();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "id":

                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            city.setId(Long.parseLong(event.toString()));
                            String id = event.asCharacters().getData();

                            logger.info("City id : "+ id);
                          //  System.out.printf("City id : %s%n",Long.parseLong(id));
                        }
                        break;
                    case "name":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            city.setName(event.toString());
                            logger.info("Name : " + event.asCharacters().getData());
                        }
                        break;
                    case "state":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            city.setState(event.toString());
                            logger.info("State : " + event.asCharacters().getData());
                        }
                        break;
                    case "country":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            city.setCountry(event.toString());
                            logger.info("Country : " + event.asCharacters().getData());
                        }
                        break;

                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();

                if (endElement.getName().getLocalPart().equals("city")) {
                    cityList.add(city);
                    city = new City();
                    logger.info("-----");
                }

            }

        }

        logger.info(cityList);
    }


}
