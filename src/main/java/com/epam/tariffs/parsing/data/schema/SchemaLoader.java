package com.epam.tariffs.parsing.data.schema;

import com.epam.tariffs.parsing.data.schema.exceptions.InvalidSchemaPathException;

import java.net.URL;

public class SchemaLoader {
    private static final String SCHEMA_PATH = "tariffs_schema.xsd";

    public URL getSchemaUrl() throws InvalidSchemaPathException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL schemaPathUrl = classLoader.getResource(SCHEMA_PATH);

        if (schemaPathUrl == null) {
            throw new InvalidSchemaPathException("");
        }

        return schemaPathUrl;
    }
}
