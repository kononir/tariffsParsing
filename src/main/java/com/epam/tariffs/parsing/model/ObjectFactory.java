package com.epam.tariffs.parsing.model;

import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.model.tariff.Tariffs;
import com.epam.tariffs.parsing.model.tariff.call.CallingTariff;
import com.epam.tariffs.parsing.model.tariff.internet.InternetTariff;
import com.epam.tariffs.parsing.model.tariff.Cost;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private static final QName TARIFF_QNAME
            = new QName("http://www.epam.com/tariffs", "tariff");
    private static final QName CALLING_TARIFF_QNAME
            = new QName("http://www.epam.com/tariffs", "calling-tariff");
    private static final QName INTERNET_TARIFF_QNAME
            = new QName("http://www.epam.com/tariffs", "internet-tariff");

    public ObjectFactory() {
    }

    public Tariffs createTariffs() {
        return new Tariffs();
    }

    public Tariff createTariff() {
        return new Tariff();
    }

    public CallingTariff createCallingTariff() {
        return new CallingTariff();
    }

    public InternetTariff createInternetTariff() {
        return new InternetTariff();
    }

    public Cost createCost() {
        return new Cost();
    }

    @XmlElementDecl(namespace = "http://www.epam.com/tariffs", name = "tariff")
    public JAXBElement<Tariff> createTariff(Tariff value) {
        return new JAXBElement<>(TARIFF_QNAME, Tariff.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.epam.com/tariffs", name = "calling-tariff",
            substitutionHeadNamespace = "http://www.epam.com/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<CallingTariff> createCallingTariff(CallingTariff value) {
        return new JAXBElement<>(CALLING_TARIFF_QNAME, CallingTariff.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.epam.com/tariffs", name = "internet-tariff",
            substitutionHeadNamespace = "http://www.epam.com/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<InternetTariff> createInternetTariff(InternetTariff value) {
        return new JAXBElement<>(INTERNET_TARIFF_QNAME, InternetTariff.class, null, value);
    }
}
