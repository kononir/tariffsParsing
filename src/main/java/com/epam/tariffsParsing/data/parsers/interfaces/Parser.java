package com.epam.tariffsParsing.data.parsers.interfaces;

import com.epam.tariffsParsing.model.Tariff;

import java.util.List;

public interface Parser {
    List<Tariff> parse(String path);
}
