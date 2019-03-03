package com.epam.tariffs.parsing;

import com.epam.tariffs.parsing.common.ParseChecker;
import com.epam.tariffs.parsing.data.parsers.creator.ParserName;
import com.epam.tariffs.parsing.data.parsers.creator.TariffsParserCreator;
import com.epam.tariffs.parsing.data.parsers.exceptions.InvalidParserNameException;
import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.interfaces.ParserCreator;
import com.epam.tariffs.parsing.data.parsers.sax.SAXTariffsParser;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.XmlValidatorImpl;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.validator.interfaces.XmlValidator;
import com.epam.tariffs.parsing.model.Cost;
import com.epam.tariffs.parsing.model.Operator;
import com.epam.tariffs.parsing.model.Tariff;
import com.epam.tariffs.parsing.model.call.CallingTariff;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class DirectorTests {
    private static final String VALID_XML = "src/test/resources/calling_tariff_all_are_filed.xml";
    private static final String INVALID_XML = "src/test/resources/invalid.xml";

    private static final ParserName PARSER_NAME = ParserName.SAX;
    private static final ParserName INVALID_PARSER_NAME = null;

    private static final String AAAAAAAB = "AAAAAAAB";
    private static final String PENSIONARY = "Пенсионный";
    private static final Operator VELCOM = Operator.VELCOM;
    private static final Cost PAYROLL_3_70 = new Cost(3, 70);
    private static final Cost CONNECTING_COST_5_0 = new Cost(5, 0);
    private static final Cost CALL_INSIDE_THE_NETWORK_0_5 = new Cost(0, 5);
    private static final Cost CALL_OUTSIDE_THE_NETWORK_0_17 = new Cost(0, 17);
    private static final Cost CALL_TO_FAVOURITE_NUMBER_0_2 = new Cost(0, 2);
    private static final int FAVORITE_NUMBERS_COUNT_5 = 5;

    private static final List<Tariff> TARIFFS = Collections.singletonList(
            new CallingTariff(
                    AAAAAAAB,
                    PENSIONARY,
                    VELCOM,
                    PAYROLL_3_70,
                    CONNECTING_COST_5_0,
                    CALL_INSIDE_THE_NETWORK_0_5,
                    CALL_OUTSIDE_THE_NETWORK_0_17,
                    CALL_TO_FAVOURITE_NUMBER_0_2,
                    FAVORITE_NUMBERS_COUNT_5
            )
    );

    @Test
    public void testManageLoadingShouldReturnListWithOneCallingTariffWhenGivenValidPath()
            throws InvalidSchemaPathException, ReadingProblemsException,
            XmlParsingException, InvalidParserNameException {
        XmlValidator validator = mock(XmlValidatorImpl.class);
        when(validator.validate(VALID_XML)).thenReturn(true);

        Parser parser = mock(SAXTariffsParser.class);
        when(parser.parse(VALID_XML)).thenReturn(TARIFFS);

        ParserCreator creator = mock(TariffsParserCreator.class);
        when(creator.create(PARSER_NAME)).thenReturn(parser);

        Director director = new Director(validator, creator);

        List<Tariff> actual = director.manageLoading(VALID_XML, PARSER_NAME);

        new ParseChecker().checkCallingTariffAllAreFilled(actual);

        verify(validator, atLeastOnce()).validate(VALID_XML);
        verify(creator, atLeastOnce()).create(PARSER_NAME);
        verify(parser, atLeastOnce()).parse(VALID_XML);
    }

    @Test
    public void testManageLoadingShouldReturnNullWhenGivenInvalidParserName()
            throws InvalidSchemaPathException, ReadingProblemsException, InvalidParserNameException {
        XmlValidator validator = mock(XmlValidatorImpl.class);
        when(validator.validate(VALID_XML)).thenReturn(true);

        ParserCreator creator = mock(TariffsParserCreator.class);
        when(creator.create(INVALID_PARSER_NAME)).thenThrow(new InvalidParserNameException("Invalid parser name!"));

        Director director = new Director(validator, creator);

        List<Tariff> tariffs = director.manageLoading(VALID_XML, INVALID_PARSER_NAME);

        Assert.assertNull(tariffs);

        verify(validator, atLeastOnce()).validate(VALID_XML);
        verify(creator, atLeastOnce()).create(INVALID_PARSER_NAME);
    }

    @Test
    public void testManageLoadingShouldReturnNullWhenGivenInvalidXml()
            throws InvalidSchemaPathException, ReadingProblemsException {
        XmlValidator validator = mock(XmlValidatorImpl.class);
        when(validator.validate(INVALID_XML)).thenReturn(false);

        ParserCreator creator = mock(TariffsParserCreator.class);

        Director director = new Director(validator, creator);

        List<Tariff> tariffs = director.manageLoading(INVALID_XML, PARSER_NAME);

        Assert.assertNull(tariffs);

        verify(validator, atLeastOnce()).validate(INVALID_XML);
    }
}
