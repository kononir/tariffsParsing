package com.epam.tariffs.parsing.data.parsers.jaxb;

import com.epam.tariffs.parsing.data.schema.SchemaLoader;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.model.tariff.Tariffs;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;
import java.util.List;

public class JaxbTariffsParser implements Parser {

    private static final String SCHEMA_LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    public List<Tariff> parse(String path) throws XmlParsingException, InvalidSchemaPathException {
        List<Tariff> result;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.epam.tariffs.parsing.model");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            URL schemaPathUrl = new SchemaLoader().getSchemaUrl();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(SCHEMA_LANGUAGE);
            Schema schema = schemaFactory.newSchema(schemaPathUrl);

            unmarshaller.setSchema(schema);

            Tariffs jaxbTariffs = (Tariffs) unmarshaller.unmarshal(new File(path));

            result = jaxbTariffs.convertToTariffs();
        } catch (JAXBException e) {
            throw new XmlParsingException("Jaxb parsing exception!", e);
        } catch (SAXException e) {
            throw new XmlParsingException("SAX parsing exception!", e);
        }

        return result;
    }
}
