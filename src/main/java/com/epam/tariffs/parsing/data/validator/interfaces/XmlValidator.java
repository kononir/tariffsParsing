package com.epam.tariffs.parsing.data.validator.interfaces;

import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;

public interface XmlValidator {
    boolean validate(String path) throws ReadingProblemsException, InvalidSchemaPathException;
}
