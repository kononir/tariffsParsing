package com.epam.tariffsParsing.model;

public class Tariff {
    private String name;
    private Operator operator;
    private Cost payroll;
    private Cost connectingCost;

    private enum Operator {
        MTS,
        VELCOM,
        LIFE
    }
}
