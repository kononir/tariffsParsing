package com.epam.tariffs.parsing.data.parsers.sax;

import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.model.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXTariffsParser implements Parser {
    public List<Tariff> parse(String path) throws XmlParsingException, ReadingProblemsException {
        List<Tariff> result;

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();

            SAXTariffsParserHandler handler = new SAXTariffsParserHandler();

            reader.setContentHandler(handler);
            reader.parse(path);

            result = handler.getTariffs();
        } catch (SAXException e) {
            throw new XmlParsingException("SAX parsing exception!", e);
        } catch (IOException e) {
            throw new ReadingProblemsException("File reading problem!", e);
        }

        return result;
    }
}
