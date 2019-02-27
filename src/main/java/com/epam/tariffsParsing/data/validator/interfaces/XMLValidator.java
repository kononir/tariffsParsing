package com.epam.tariffsParsing.data.validator.interfaces;

import com.epam.tariffsParsing.data.validator.exceptions.InvalidFileToParsingException;
import com.epam.tariffsParsing.data.validator.exceptions.ReadingProblemsException;

public interface XMLValidator {
    void validate(String path) throws InvalidFileToParsingException, ReadingProblemsException;
}
