package com.epam.tariffs.parsing.data.parsers;

import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.jaxb.JaxbTariffsParser;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import org.junit.Test;

import java.util.List;

public class JaxbTariffsParserTests {
    private static final String PATH_OF_VALID_FILE = "src/test/resources/one_tariff.xml";

    @Test
    public void testParseShouldReturnListWithOneTariffWhenGivenPathOfValidFile()
            throws XmlParsingException, InvalidSchemaPathException, ReadingProblemsException {
        Parser parser = new JaxbTariffsParser();

        List<Tariff> tariffs = parser.parse(PATH_OF_VALID_FILE);

        new ParserValidFileChecking().check(tariffs);
    }
}
