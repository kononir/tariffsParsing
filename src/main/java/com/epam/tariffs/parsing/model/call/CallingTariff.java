package com.epam.tariffs.parsing.model.call;

import com.epam.tariffs.parsing.model.Cost;
import com.epam.tariffs.parsing.model.Tariff;
import com.epam.tariffs.parsing.model.Operator;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallingTariff", propOrder = {
        "callInsideTheNetwork",
        "callOutsideTheNetwork",
        "callToFavoriteNumbers",
        "favoriteNumbersCount"
})

public class CallingTariff extends Tariff {
    @XmlElement(name = "call-inside-the-network", namespace = "http://www.epam.com/tariffs", required = true)
    private Cost callInsideTheNetwork;
    @XmlElement(name = "call-outside-the-network", namespace = "http://www.epam.com/tariffs", required = true)
    private Cost callOutsideTheNetwork;
    @XmlElement(name = "call-to-favourite-number", namespace = "http://www.epam.com/tariffs", required = true)
    private Cost callToFavoriteNumbers;
    @XmlElement(name = "favorite-numbers-count", namespace = "http://www.epam.com/tariffs", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
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
