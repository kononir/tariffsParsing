package com.epam.tariffs.parsing.data.parsers.creator;

import com.epam.tariffs.parsing.data.parsers.dom.DOMTariffsParser;
import com.epam.tariffs.parsing.data.parsers.exceptions.InvalidParserNameException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.interfaces.ParserCreator;
import com.epam.tariffs.parsing.data.parsers.jaxb.JaxbTariffsParser;
import com.epam.tariffs.parsing.data.parsers.sax.SAXTariffsParser;
import org.junit.Assert;
import org.junit.Test;

public class TariffsParserCreatorTests {
    private static final ParserName DOM = ParserName.DOM;
    private static final ParserName SAX = ParserName.SAX;
    private static final ParserName JAXB = ParserName.JAXB;
    private static final ParserName INVALID_NAME = null;

    @Test
    public void testCreateShouldReturnDOMTariffsParserWhenGivenDOM() throws InvalidParserNameException {
        ParserCreator creator = new TariffsParserCreator();

        Parser parser = creator.create(DOM);

        Assert.assertEquals(DOMTariffsParser.class, parser.getClass());
    }

    @Test
    public void testCreateShouldReturnSAXTariffsParserWhenGivenSAX() throws InvalidParserNameException {
        ParserCreator creator = new TariffsParserCreator();

        Parser parser = creator.create(SAX);

        Assert.assertEquals(SAXTariffsParser.class, parser.getClass());
    }

    @Test
    public void testCreateShouldReturnJaxbTariffsParserWhenGivenJAXB() throws InvalidParserNameException {
        ParserCreator creator = new TariffsParserCreator();

        Parser parser = creator.create(JAXB);

        Assert.assertEquals(JaxbTariffsParser.class, parser.getClass());
    }

    @Test(expected = InvalidParserNameException.class)
    public void testCreateShouldThrowInvalidParserNameExceptionWhenGivenInvalid() throws InvalidParserNameException {
        ParserCreator creator = new TariffsParserCreator();

        creator.create(INVALID_NAME);

        Assert.fail();
    }
}
