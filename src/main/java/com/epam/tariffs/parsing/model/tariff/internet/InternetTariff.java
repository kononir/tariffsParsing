package com.epam.tariffs.parsing.model.tariff.internet;

import com.epam.tariffs.parsing.model.tariff.Cost;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.model.tariff.Operator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternetTariff", propOrder = {
        "startingNumberOfMB",
        "costOfOneMB",
        "speedType"
})
public class InternetTariff extends Tariff {
    @XmlElement(name = "starting-number-of-MB", required = true)
    private int startingNumberOfMB;
    @XmlElement(name = "cost-of-one-MB", required = true)
    private Cost costOfOneMB;
    @XmlElement(name = "speed-type", required = true)
    private SpeedType speedType;

    public InternetTariff() {
    }

    public InternetTariff(String id, String name, Operator operator, Cost payroll,
                          Cost connectingCost, int startingNumberOfMB, Cost costOfOneMB,
                          SpeedType speedType) {
        super(id, name, operator, payroll, connectingCost);
        this.startingNumberOfMB = startingNumberOfMB;
        this.costOfOneMB = costOfOneMB;
        this.speedType = speedType;
    }

    public int getStartingNumberOfMB() {
        return startingNumberOfMB;
    }

    public void setStartingNumberOfMB(int startingNumberOfMB) {
        this.startingNumberOfMB = startingNumberOfMB;
    }

    public Cost getCostOfOneMB() {
        return costOfOneMB;
    }

    public void setCostOfOneMB(Cost costOfOneMB) {
        this.costOfOneMB = costOfOneMB;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }

    @Override
    public String toString() {
        return "InternetTariff{" + super.toString() +
                "startingNumberOfMB=" + startingNumberOfMB +
                ", costOfOneMB=" + costOfOneMB +
                ", speedType=" + speedType +
                '}';
    }
}
