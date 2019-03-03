package com.epam.tariffs.parsing.model.internet;

import com.epam.tariffs.parsing.data.parsers.interfaces.ValuableEnumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "InternetSpeedType", namespace = "http://www.epam.com/tariffs")
public enum SpeedType implements ValuableEnumeration {
    @XmlEnumValue(value = "unlimited")
    UNLIMITED("unlimited"),
    @XmlEnumValue(value = "limited")
    LIMITED("limited");

    private String value;

    SpeedType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
