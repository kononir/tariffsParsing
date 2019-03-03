package com.epam.tariffs.parsing.data.validator;

import com.epam.tariffs.parsing.data.validator.exceptions.ReadingProblemsException;
import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;
import com.epam.tariffs.parsing.data.validator.interfaces.XmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorImplTests {
    private static final String TARIFFS_XML = "src/test/resources/tariffs.xml";
    private static final String INVALID_XML = "src/test/resources/invalid.xml";
    private static final String INVALID_PATH = "Invalid path!";

    @Test
    public void testValidateShouldNotThrowInvalidFileToParsingExceptionWhenFileDataIsValid()
            throws ReadingProblemsException, InvalidSchemaPathException {
        XmlValidator validator = new XmlValidatorImpl();

        boolean actual = validator.validate(TARIFFS_XML);

        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateShouldThrowInvalidFileToParsingExceptionWhenFileDataIsNotValid()
            throws ReadingProblemsException, InvalidSchemaPathException {
        XmlValidator validator = new XmlValidatorImpl();

        boolean actual = validator.validate(INVALID_XML);

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
