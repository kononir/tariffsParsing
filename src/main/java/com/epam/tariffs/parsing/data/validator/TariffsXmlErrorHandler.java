package com.epam.tariffs.parsing.data.validator;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TariffsXmlErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = Logger.getLogger("xmlErrorLogger");

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.warn(getWholeMessage(exception));

        throw exception;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        LOGGER.error(getWholeMessage(exception));

        throw exception;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.fatal(getWholeMessage(exception));

        throw exception;
    }

    private String getWholeMessage(SAXParseException exception) {
        return "Line: " + exception.getLineNumber()
                + ", Column: " + exception.getColumnNumber()
                + " - " + exception.getMessage();
    }
}
