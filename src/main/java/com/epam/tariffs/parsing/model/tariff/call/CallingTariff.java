package com.epam.tariffs.parsing.model.tariff.call;

import com.epam.tariffs.parsing.model.tariff.Cost;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.model.tariff.Operator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallingTariff", propOrder = {
        "callInsideTheNetwork",
        "callOutsideTheNetwork",
        "callToFavoriteNumbers",
        "favoriteNumbersCount"
})

public class CallingTariff extends Tariff {
    @XmlElement(name = "call-inside-the-network", required = true)
    private Cost callInsideTheNetwork;
    @XmlElement(name = "call-outside-the-network", required = true)
    private Cost callOutsideTheNetwork;
    @XmlElement(name = "call-to-favourite-number", required = true)
    private Cost callToFavoriteNumbers;
    @XmlElement(name = "favorite-number-count", required = true)
    private int favoriteNumbersCount;

    public CallingTariff() {
    }

    public CallingTariff(String id, String name, Operator operator, Cost payroll,
                         Cost connectingCost, Cost callInsideTheNetwork, Cost callOutsideTheNetwork,
                         Cost callToFavoriteNumbers, int favoriteNumbersCount) {
        super(id, name, operator, payroll, connectingCost);
        this.callInsideTheNetwork = callInsideTheNetwork;
        this.callOutsideTheNetwork = callOutsideTheNetwork;
        this.callToFavoriteNumbers = callToFavoriteNumbers;
        this.favoriteNumbersCount = favoriteNumbersCount;
    }

    public Cost getCallInsideTheNetwork() {
        return callInsideTheNetwork;
    }

    public void setCallInsideTheNetwork(Cost callInsideTheNetwork) {
        this.callInsideTheNetwork = callInsideTheNetwork;
    }

    public Cost getCallOutsideTheNetwork() {
        return callOutsideTheNetwork;
    }

    public void setCallOutsideTheNetwork(Cost callOutsideTheNetwork) {
        this.callOutsideTheNetwork = callOutsideTheNetwork;
    }

    public Cost getCallToFavoriteNumbers() {
        return callToFavoriteNumbers;
    }

    public void setCallToFavoriteNumbers(Cost callToFavoriteNumbers) {
        this.callToFavoriteNumbers = callToFavoriteNumbers;
    }

    public int getFavoriteNumbersCount() {
        return favoriteNumbersCount;
    }

    public void setFavoriteNumbersCount(int favoriteNumbersCount) {
        this.favoriteNumbersCount = favoriteNumbersCount;
    }

    @Override
    public String toString() {
        return "CallingTariff{" + super.toString() +
                ", callInsideTheNetwork=" + callInsideTheNetwork +
                ", callOutsideTheNetwork=" + callOutsideTheNetwork +
                ", callToFavoriteNumbers=" + callToFavoriteNumbers +
                ", favoriteNumbersCount=" + favoriteNumbersCount +
                '}';
    }
}
