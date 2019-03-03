package com.epam.tariffs.parsing.data.parsers.dom;

import com.epam.tariffs.parsing.model.Cost;
import com.epam.tariffs.parsing.model.Operator;
import com.epam.tariffs.parsing.model.call.CallingTariff;
import com.epam.tariffs.parsing.model.internet.InternetTariff;
import com.epam.tariffs.parsing.model.internet.SpeedType;
import com.epam.tariffs.parsing.util.EnumSearcher;
import com.epam.tariffs.parsing.model.Tariff;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TariffsDocumentBuilder {
    private Document document;

    public TariffsDocumentBuilder(Document document) {
        this.document = document;
    }

    public List<Tariff> buildList() {
        List<Tariff> tariffs = new ArrayList<>();

        Element root = document.getDocumentElement();
        NodeList callingTariffNodes = root.getElementsByTagName("calling-tariff");

        for (int i = 0; i < callingTariffNodes.getLength(); i++) {
            Tariff tariff = buildCallingTariff((Element) callingTariffNodes.item(i));

            tariffs.add(tariff);
        }

        NodeList internetTariffNodes = root.getElementsByTagName("internet-tariff");

        for (int i = 0; i < internetTariffNodes.getLength(); i++) {
            Tariff tariff = buildInternetTariff((Element) internetTariffNodes.item(i));

            tariffs.add(tariff);
        }

        return tariffs;
    }

    private CallingTariff buildCallingTariff(Element tariffElement) {
        CallingTariff callingTariff = new CallingTariff();

        setAttributes(callingTariff, tariffElement);
        setTariffElements(callingTariff, tariffElement);
        setCallingTariffElements(callingTariff, tariffElement);

        return callingTariff;
    }

    private void setCallingTariffElements(CallingTariff tariff, Element tariffElement) {
        Cost callInsideTheNetwork = newCost(getFirstElementByTagName(tariffElement, "call-inside-the-network"));
        tariff.setCallInsideTheNetwork(callInsideTheNetwork);

        Cost callOutsideTheNetwork = newCost(getFirstElementByTagName(tariffElement, "call-outside-the-network"));
        tariff.setCallOutsideTheNetwork(callOutsideTheNetwork);

        Cost callToFavouriteNumber = newCost(getFirstElementByTagName(tariffElement, "call-to-favourite-number"));
        tariff.setCallToFavoriteNumbers(callToFavouriteNumber);

        int favoriteNumbersCount = newInt(getFirstElementByTagName(tariffElement, "favorite-numbers-count"));
        tariff.setFavoriteNumbersCount(favoriteNumbersCount);
    }

    private InternetTariff buildInternetTariff(Element tariffElement) {
        InternetTariff internetTariff = new InternetTariff();

        setAttributes(internetTariff, tariffElement);
        setTariffElements(internetTariff, tariffElement);
        setInternetTariffElements(internetTariff, tariffElement);

        return internetTariff;
    }

    private void setInternetTariffElements(InternetTariff tariff, Element tariffElement) {
        Cost costOfOneMB = newCost(getFirstElementByTagName(tariffElement, "cost-of-one-MB"));
        tariff.setCostOfOneMB(costOfOneMB);

        int startingNumberOfMB = newInt(getFirstElementByTagName(tariffElement, "starting-number-of-MB"));
        tariff.setStartingNumberOfMB(startingNumberOfMB);

        String speedTypeStr = newString(getFirstElementByTagName(tariffElement, "speed-type"));
        List<SpeedType> speedTypes = Arrays.asList(SpeedType.values());
        SpeedType speedType = new EnumSearcher<>(speedTypes).search(speedTypeStr);
        tariff.setSpeedType(speedType);
    }

    private void setAttributes(Tariff tariff, Element tariffElement) {
        tariff.setId(tariffElement.getAttribute("id"));
        tariff.setName(tariffElement.getAttribute("name"));

        String operatorStr = tariffElement.getAttribute("operator");

        List<Operator> operators = Arrays.asList(Operator.values());

        Operator operator = new EnumSearcher<>(operators).search(operatorStr);

        tariff.setOperator(operator);
    }

    private void setTariffElements(Tariff tariff, Element tariffElement) {
        Cost payroll = newCost(getFirstElementByTagName(tariffElement, "payroll"));
        tariff.setPayroll(payroll);

        Cost connectingCost = newCost(getFirstElementByTagName(tariffElement, "connecting-cost"));
        tariff.setConnectingCost(connectingCost);
    }

    private Cost newCost(Element costElement) {
        Cost cost = new Cost();

        Element rublesElement = getFirstElementByTagName(costElement, "rubles");
        int rubles = Integer.parseInt(rublesElement.getTextContent());

        cost.setRubles(rubles);

        Element kopecksElement = getFirstElementByTagName(costElement, "kopecks");
        int kopecks = Integer.parseInt(kopecksElement.getTextContent());

        cost.setKopecks(kopecks);

        return cost;
    }

    private int newInt(Element intElement) {
        return Integer.parseInt(intElement.getTextContent());
    }

    private String newString(Element stringElement) {
        return stringElement.getTextContent();
    }

    private Element getFirstElementByTagName(Element element, String tagName) {
        int first = 0;
        NodeList nodes = element.getElementsByTagName(tagName);

        return (Element) nodes.item(first);
    }
}
