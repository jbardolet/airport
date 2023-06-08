package com.solvd.hospital.parserXML;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidateCIty {
    private static final String FILENAME = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "java"+File.separator+ "com"+File.separator+ "solvd"+File.separator+ "hospital"+File.separator+ "parserXML"+File.separator+"city.xml";
    private static final String VALIDATORNAME = System.getProperty("user.dir") + File.separator + "src"+File.separator+ "main"+File.separator+ "java"+File.separator+ "com"+File.separator+ "solvd"+File.separator+ "hospital"+File.separator+ "parserXML"+File.separator+"XSDcity.xml";



    public static void main(String[] args) {
        System.out.println(validateXMLSchema(VALIDATORNAME,FILENAME));
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
