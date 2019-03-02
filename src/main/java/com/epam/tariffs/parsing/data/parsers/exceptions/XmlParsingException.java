package com.epam.tariffs.parsing.data.parsers.exceptions;

public class XmlParsingException extends Exception {
    public XmlParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParsingException(String message) {
        super(message);
    }
}
