package com.epam.tariffsParsing.data.validator;

import com.epam.tariffsParsing.data.validator.exceptions.InvalidFileToParsingException;
import com.epam.tariffsParsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffsParsing.data.validator.exceptions.SchemaIsNotFoundException;
import com.epam.tariffsParsing.data.validator.interfaces.XMLValidator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidatorImpl implements XMLValidator {
    public void validate(String filePath)
            throws InvalidFileToParsingException, ReadingProblemsException {
        try {
            String schemaPath = "src/main/resources/tariffs_schema.xsd";

            String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);
            Schema schema = schemaFactory.newSchema(new File(schemaPath));

            Validator validator = schema.newValidator();

            Source source = new StreamSource(filePath);
            validator.validate(source);
        } catch (SAXException e) {
            throw new InvalidFileToParsingException("This file is invalid to parse!", e);
        } catch (IOException e) {
            throw new ReadingProblemsException("File reading problem!", e);
        }
    }
}
