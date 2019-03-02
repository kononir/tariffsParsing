package com.epam.tariffs.parsing.model.tariff;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cost", propOrder = {
        "rubles",
        "kopecks"
})
public class Cost {
    @XmlElement(required = true)
    private int rubles;
    @XmlElement(required = true)
    private int kopecks;

    public Cost() {
    }

    public Cost(int rubles, int kopecks) {
        this.rubles = rubles;
        this.kopecks = kopecks;
    }

    public int getRubles() {
        return rubles;
    }

    public void setRubles(int rubles) {
        this.rubles = rubles;
    }

    public int getKopecks() {
        return kopecks;
    }

    public void setKopecks(int kopecks) {
        this.kopecks = kopecks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cost cost = (Cost) o;

        return rubles == cost.rubles &&
                kopecks == cost.kopecks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rubles, kopecks);
    }

    @Override
    public String toString() {
        return "Cost{" +
                "rubles=" + rubles +
                ", kopecks=" + kopecks +
                '}';
    }
}
