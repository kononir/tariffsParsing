package com.epam.tariffsParsing.model;

public class InternetTariff extends Tariff {
    private int startingNumberOfMB;
    private Cost costOfOneMB;
    private InternetSpeedType speedType;

    private enum InternetSpeedType {
        UNLIMITED,
        LIMITED
    }
}
