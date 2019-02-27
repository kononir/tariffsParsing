package com.epam.tariffsParsing.data.validator;

import com.epam.tariffsParsing.data.validator.exceptions.InvalidFileToParsingException;
import com.epam.tariffsParsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffsParsing.data.validator.exceptions.SchemaIsNotFoundException;
import com.epam.tariffsParsing.data.validator.interfaces.XMLValidator;
import org.junit.Assert;
import org.junit.Test;

public class XMLValidatorImplTests {
    private static final String PATH_OF_VALID_FILE = "src/test/resources/tariffs.xml";
    private static final String PATH_OF_INVALID_FILE = "src/test/resources/invalid.xml";
    private static final String INVALID_PATH = "Invalid path!";

    @Test
    public void testValidateShouldNotThrowInvalidFileToParsingExceptionWhenFileDataIsValid()
            throws InvalidFileToParsingException, ReadingProblemsException {
        XMLValidator validator = new XMLValidatorImpl();

        validator.validate(PATH_OF_VALID_FILE);
    }

    @Test (expected = InvalidFileToParsingException.class)
    public void testValidateShouldThrowInvalidFileToParsingExceptionWhenFileDataIsNotValid()
            throws ReadingProblemsException, InvalidFileToParsingException {
        XMLValidator validator = new XMLValidatorImpl();

        validator.validate(PATH_OF_INVALID_FILE);

        Assert.fail();
    }

    @Test (expected = ReadingProblemsException.class)
    public void testValidateShouldThrowReadingProblemsExceptionWhenFileNotFound()
            throws ReadingProblemsException, InvalidFileToParsingException {
        XMLValidator validator = new XMLValidatorImpl();

        validator.validate(INVALID_PATH);

        Assert.fail();
    }
}
