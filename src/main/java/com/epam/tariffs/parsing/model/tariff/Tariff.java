package com.epam.tariffs.parsing.model.tariff;

import com.epam.tariffs.parsing.model.tariff.call.CallingTariff;
import com.epam.tariffs.parsing.model.tariff.internet.InternetTariff;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tariff", propOrder = {
        "payroll",
        "connectingCost"
})
@XmlSeeAlso({
        CallingTariff.class,
        InternetTariff.class
})
public class Tariff {
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Operator operator;
    @XmlElement(namespace = "http://www.epam.com/tariffs", required = true)
    private Cost payroll;
    @XmlElement(name = "connecting-cost", namespace = "http://www.epam.com/tariffs", required = true)
    private Cost connectingCost;

    public Tariff() {
    }

    public Tariff(String id, String name, Operator operator, Cost payroll, Cost connectingCost) {
        this.id = id;
        this.name = name;
        this.operator = operator;
        this.payroll = payroll;
        this.connectingCost = connectingCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperator() {
        if (operator == null) {
            return Operator.MTS;
        } else {
            return operator;
        }
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Cost getPayroll() {
        return payroll;
    }

    public void setPayroll(Cost payroll) {
        this.payroll = payroll;
    }

    public Cost getConnectingCost() {
        return connectingCost;
    }

    public void setConnectingCost(Cost connectingCost) {
        this.connectingCost = connectingCost;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", operator=" + operator +
                ", payroll=" + payroll +
                ", connectingCost=" + connectingCost;
    }
}
