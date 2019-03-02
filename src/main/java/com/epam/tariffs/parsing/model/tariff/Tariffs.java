package com.epam.tariffs.parsing.model.tariff;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "tariffList"
})
@XmlRootElement(name = "tariffs")
public class Tariffs {
    @XmlElementRef(name = "tariff", namespace = "http://www.epam.com/tariffs", type = JAXBElement.class)
    private List<JAXBElement<? extends Tariff>> tariffList;

    public Tariffs() {
    }

    public List<JAXBElement<? extends Tariff>> getTariffList() {
        if (tariffList == null) {
            tariffList = new ArrayList<>();
        }

        return tariffList;
    }

    public List<Tariff> convertToTariffs() {
        List<Tariff> tariffs = new ArrayList<>();

        for (JAXBElement<? extends Tariff> jaxbElement: tariffList) {
            tariffs.add(jaxbElement.getValue());
        }

        return tariffs;
    }
}
