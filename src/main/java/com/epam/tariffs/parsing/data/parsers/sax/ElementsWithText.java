package com.epam.tariffs.parsing.data.parsers.sax;

import com.epam.tariffs.parsing.util.ValuableEnumeration;

public enum ElementsWithText implements ValuableEnumeration {
    FAVOURITE_NUMBERS_COUNT("favorite-numbers-count"),
    STARTING_NUMBER_OF_MB("starting-number-of-mb"),
    SPEED_TYPE("speed-type"),
    RUBLES("rubles"),
    KOPECKS("kopecks");

    private String value;

    ElementsWithText(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
