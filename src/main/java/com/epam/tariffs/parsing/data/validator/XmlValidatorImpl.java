package com.epam.tariffs.parsing.data.validator;

import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.schema.SchemaLoader;
import com.epam.tariffs.parsing.data.validator.interfaces.XmlValidator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class XmlValidatorImpl implements XmlValidator {
    private static final String SCHEMA_LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    @Override
    public boolean validate(String filePath) throws ReadingProblemsException, InvalidSchemaPathException {
        boolean answer;

        try {
            URL schemaPathUrl = new SchemaLoader().getSchemaUrl();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(SCHEMA_LANGUAGE);
            Schema schema = schemaFactory.newSchema(schemaPathUrl);

            Validator validator = schema.newValidator();

            Source source = new StreamSource(filePath);
            validator.validate(source);

            answer = true;
        } catch (SAXException e) {
            answer = false;
        } catch (IOException e) {
            throw new ReadingProblemsException("File reading problem!", e);
        }

        return answer;
    }
}
