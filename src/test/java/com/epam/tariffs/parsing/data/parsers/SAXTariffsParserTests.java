package com.epam.tariffs.parsing.data.parsers;

import com.epam.tariffs.parsing.common.ParseChecker;
import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.sax.SAXTariffsParser;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import org.junit.Test;

import java.util.List;

public class SAXTariffsParserTests {
    private static final String CALLING_TARIFF_ALL_ARE_FILED_XML
            = "src/test/resources/calling_tariff_all_are_filed.xml";
    private static final String CALLING_TARIFF_DEFAULT_OPERATOR_XML
            = "src/test/resources/calling_tariff_default_operator.xml";
    private static final String INTERNET_TARIFF_ALL_ARE_FILED_XML
            = "src/test/resources/internet_tariff_all_are_filed.xml";

    @Test
    public void testParseShouldReturnListWithOneTariffWhenGivenPathOfCallingTariffWhereAllFieldsAreFilled()
            throws XmlParsingException, InvalidSchemaPathException, ReadingProblemsException {
        Parser parser = new SAXTariffsParser();

        List<Tariff> tariffs = parser.parse(CALLING_TARIFF_ALL_ARE_FILED_XML);

        new ParseChecker().checkCallingTariffAllAreFilled(tariffs);
    }

    @Test
    public void testParseShouldReturnListWithOneTariffWhenGivenPathOfCallingTariffWithDefaultOperator()
            throws XmlParsingException, InvalidSchemaPathException, ReadingProblemsException {
        Parser parser = new SAXTariffsParser();

        List<Tariff> tariffs = parser.parse(CALLING_TARIFF_DEFAULT_OPERATOR_XML);

        new ParseChecker().checkCallingTariffDefaultOperator(tariffs);
    }

    @Test
    public void testParseShouldReturnListWithOneTariffWhenGivenPathOfInternetTariffWhereAllFieldsAreFilled()
            throws XmlParsingException, InvalidSchemaPathException, ReadingProblemsException {
        Parser parser = new SAXTariffsParser();

        List<Tariff> tariffs = parser.parse(INTERNET_TARIFF_ALL_ARE_FILED_XML);

        new ParseChecker().checkInternetTariffAllAreFilled(tariffs);
    }
}
