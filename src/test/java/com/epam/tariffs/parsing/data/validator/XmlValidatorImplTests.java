package com.epam.tariffs.parsing.data.validator;

import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.interfaces.XmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorImplTests {
    private static final String PATH_OF_VALID_FILE = "src/test/resources/tariffs.xml";
    private static final String PATH_OF_INVALID_FILE = "src/test/resources/invalid.xml";
    private static final String INVALID_PATH = "Invalid path!";

    @Test
    public void testValidateShouldNotThrowInvalidFileToParsingExceptionWhenFileDataIsValid()
            throws ReadingProblemsException, InvalidSchemaPathException {
        XmlValidator validator = new XmlValidatorImpl();

        boolean actual = validator.validate(PATH_OF_VALID_FILE);

        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateShouldThrowInvalidFileToParsingExceptionWhenFileDataIsNotValid()
            throws ReadingProblemsException, InvalidSchemaPathException {
        XmlValidator validator = new XmlValidatorImpl();

        boolean actual = validator.validate(PATH_OF_INVALID_FILE);

        Assert.assertFalse(actual);
    }

    @Test (expected = ReadingProblemsException.class)
    public void testValidateShouldThrowReadingProblemsExceptionWhenFileNotFound()
            throws ReadingProblemsException, InvalidSchemaPathException {
        XmlValidator validator = new XmlValidatorImpl();

        validator.validate(INVALID_PATH);

        Assert.fail();
    }
}
