package com.epam.tariffs.parsing.data.parsers.dom;

import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class DOMTariffsParser implements Parser {
    public List<Tariff> parse(String path) throws XmlParsingException, ReadingProblemsException {
        List<Tariff> tariffs;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document document = documentBuilder.parse(path);

            tariffs = new TariffsDocumentBuilder(document).buildList();
        } catch (ParserConfigurationException | SAXException e) {
            throw new XmlParsingException("DOM parsing exception!", e);
        } catch (IOException e) {
            throw new ReadingProblemsException("File reading problem!", e);
        }

        return tariffs;
    }
}
