package com.epam.tariffs.parsing.data.parsers.interfaces;

import com.epam.tariffs.parsing.data.parsers.exceptions.XmlParsingException;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.model.tariff.Tariff;

import java.util.List;

public interface Parser {
    List<Tariff> parse(String path) throws XmlParsingException, InvalidSchemaPathException, ReadingProblemsException;
}
