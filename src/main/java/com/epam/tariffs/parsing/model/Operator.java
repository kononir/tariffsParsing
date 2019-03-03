package com.epam.tariffs.parsing.model;

import com.epam.tariffs.parsing.data.parsers.interfaces.ValuableEnumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "Operator", namespace = "http://www.epam.com/tariffs")
public enum Operator implements ValuableEnumeration {
    @XmlEnumValue(value = "MTS")
    MTS("mts"),
    @XmlEnumValue(value = "Velcom")
    VELCOM("velcom"),
    @XmlEnumValue(value = "Life")
    LIFE("life");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
