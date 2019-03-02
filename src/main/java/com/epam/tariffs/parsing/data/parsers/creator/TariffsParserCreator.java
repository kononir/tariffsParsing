package com.epam.tariffs.parsing.data.parsers.creator;

import com.epam.tariffs.parsing.data.parsers.exceptions.InvalidParserNameException;
import com.epam.tariffs.parsing.data.parsers.jaxb.JaxbTariffsParser;
import com.epam.tariffs.parsing.data.parsers.dom.DOMTariffsParser;
import com.epam.tariffs.parsing.data.parsers.sax.SAXTariffsParser;
import com.epam.tariffs.parsing.data.parsers.stax.StAXTariffsParser;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.interfaces.ParserCreator;

public class TariffsParserCreator implements ParserCreator {

    @Override
    public Parser create(ParserName parserName) throws InvalidParserNameException {
        if (parserName == null) {
            throw new InvalidParserNameException("Parser name is null!");
        }

        Parser result;

        switch(parserName) {
            case DOM:
                result = new DOMTariffsParser();
                break;
            case SAX:
                result = new SAXTariffsParser();
                break;
            case JAXB:
                result = new JaxbTariffsParser();
                break;
            case STAX:
                result = new StAXTariffsParser();
                break;
            default:
                throw new InvalidParserNameException("Parser name is undefined!");
        }

        return result;
    }
}
