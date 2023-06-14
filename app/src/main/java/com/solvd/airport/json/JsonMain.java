package com.solvd.airport.json;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.airport.json.classes.Ticket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonMain {
    private static final Logger logger = LogManager.getLogger("JsonMain");
    private static final String FILENAME = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "resources"+File.separator+"tickets.json";


    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            List<Ticket> tickets = objectMapper.readValue(new File(FILENAME), new TypeReference<List<Ticket>>() {});
            for(Ticket t:tickets){
                logger.info(t.getId());
                logger.info(t.getLuggage().getDescription());
                logger.info(t.getFood());
                logger.info(t.getSeat().getSeatLetter());
                logger.info(t.getPerson());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
