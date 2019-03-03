package com.epam.tariffs.parsing.data.parsers.sax;

import com.epam.tariffs.parsing.util.ValuableEnumeration;

public enum ElementsWithoutText implements ValuableEnumeration {
    PAYROLL("payroll"),
    CONNECTING_COST("connecting-cost"),
    CALL_INSIDE_THE_NETWORK("call-inside-the-network"),
    CALL_OUTSIDE_THE_NETWORK("call-outside-the-network"),
    CALL_TO_FAVOURITE_NUMBER("call-to-favourite-number"),
    COST_OF_ONE_MB("cost-of-one-MB");

    private String value;

    ElementsWithoutText(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
