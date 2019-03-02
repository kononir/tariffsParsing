package com.epam.tariffs.parsing.data.parsers.interfaces;

import com.epam.tariffs.parsing.data.parsers.creator.ParserName;
import com.epam.tariffs.parsing.data.parsers.exceptions.InvalidParserNameException;

public interface ParserCreator {
    Parser create(ParserName parserName) throws InvalidParserNameException;
}
