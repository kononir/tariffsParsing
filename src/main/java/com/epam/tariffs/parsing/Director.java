package com.epam.tariffs.parsing;

import com.epam.tariffs.parsing.data.parsers.creator.ParserName;
import com.epam.tariffs.parsing.data.parsers.exceptions.InvalidParserNameException;
import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.parsers.interfaces.Parser;
import com.epam.tariffs.parsing.data.parsers.interfaces.ParserCreator;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.validator.interfaces.XmlValidator;
import com.epam.tariffs.parsing.model.Tariff;
import org.apache.log4j.Logger;

import java.util.List;

public class Director {
    private XmlValidator xmlValidator;
    private ParserCreator parserCreator;

    private static final Logger LOGGER = Logger.getLogger(Director.class);

    public Director(XmlValidator xmlValidator, ParserCreator parserCreator) {
        this.xmlValidator = xmlValidator;
        this.parserCreator = parserCreator;
    }

    public List<Tariff> manageLoading(String path, ParserName parserName) {
        List<Tariff> tariffs = null;

        try {
            if (!xmlValidator.validate(path)) {
                throw new InvalidFileToParsingException("This file is invalid!");
            }

            Parser parser = parserCreator.create(parserName);

            tariffs = parser.parse(path);
        } catch (ReadingProblemsException | InvalidSchemaPathException e) {
            LOGGER.fatal(e);
        } catch (InvalidFileToParsingException | InvalidParserNameException | XmlParsingException e) {
            LOGGER.error(e);
        }

        return tariffs;
    }
}
