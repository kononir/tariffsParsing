package com.epam.tariffs.parsing.data.parsers.sax;

import com.epam.tariffs.parsing.model.Cost;
import com.epam.tariffs.parsing.util.EnumSearcher;
import com.epam.tariffs.parsing.model.Operator;
import com.epam.tariffs.parsing.model.Tariff;
import com.epam.tariffs.parsing.model.call.CallingTariff;
import com.epam.tariffs.parsing.model.internet.InternetTariff;
import com.epam.tariffs.parsing.model.internet.SpeedType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SAXTariffsParserHandler extends DefaultHandler {
    private List<Tariff> tariffs = new ArrayList<>();
    private Tariff currentTariff;
    private ElementsWithText currentElementWithText;
    private ElementsWithoutText currentElementWithoutText;

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("calling-tariff".equals(localName)) {
            newCallingTariff();
            setAttributes(attributes);
        } else if ("internet-tariff".equals(localName)) {
            newInternetTariff();
            setAttributes(attributes);
        } else {
            changeCurrentElementName(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("calling-tariff".equals(localName) || "internet-tariff".equals(localName)) {
            tariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String line = new String(ch, start, length);

        if (currentElementWithText != null) {
            setCurrentElementValue(line);
        }

        currentElementWithText = null;
    }

    private void newCallingTariff() {
        CallingTariff callingTariff = new CallingTariff();
        callingTariff.setPayroll(new Cost());
        callingTariff.setConnectingCost(new Cost());
        callingTariff.setCallInsideTheNetwork(new Cost());
        callingTariff.setCallOutsideTheNetwork(new Cost());
        callingTariff.setCallToFavoriteNumbers(new Cost());

        currentTariff = callingTariff;
    }

    private void newInternetTariff() {
        InternetTariff internetTariff = new InternetTariff();
        internetTariff.setPayroll(new Cost());
        internetTariff.setConnectingCost(new Cost());
        internetTariff.setCostOfOneMB(new Cost());

        currentTariff = internetTariff;
    }

    private void changeCurrentElementName(String localName) {
        List<ElementsWithText> elementsWithText = Arrays.asList(ElementsWithText.values());

        ElementsWithText elementWithText = new EnumSearcher<>(elementsWithText).search(localName);

        if (elementWithText != null) {
            currentElementWithText = elementWithText;
        } else {
            List<ElementsWithoutText> elementsWithoutTexts = Arrays.asList(ElementsWithoutText.values());

            currentElementWithoutText = new EnumSearcher<>(elementsWithoutTexts).search(localName);
        }
    }

    private void setAttributes(Attributes attributes) {
        currentTariff.setId(attributes.getValue("id"));
        currentTariff.setName(attributes.getValue("name"));

        String operatorStr = attributes.getValue("operator");
        List<Operator> operators = Arrays.asList(Operator.values());
        Operator operator = new EnumSearcher<>(operators).search(operatorStr);

        currentTariff.setOperator(operator);
    }

    private void setCurrentElementValue(String line) {
        switch (currentElementWithText) {
            case FAVOURITE_NUMBERS_COUNT:
                int favoriteNumbersCount = Integer.parseInt(line);
                ((CallingTariff) currentTariff).setFavoriteNumbersCount(favoriteNumbersCount);

                break;
            case STARTING_NUMBER_OF_MB:
                int startingNumberOfMB = Integer.parseInt(line);
                ((InternetTariff) currentTariff).setStartingNumberOfMB(startingNumberOfMB);

                break;
            case SPEED_TYPE:
                List<SpeedType> elements = Arrays.asList(SpeedType.values());

                SpeedType speedType = new EnumSearcher<>(elements).search(line);

                ((InternetTariff) currentTariff).setSpeedType(speedType);

                break;
            case RUBLES:
                int rubles = Integer.parseInt(line);

                Cost costToRubles = getCost();
                costToRubles.setRubles(rubles);

                break;
            case KOPECKS:
                int kopecks = Integer.parseInt(line);

                Cost costToKopecks = getCost();
                costToKopecks.setKopecks(kopecks);

                break;
            default:
                throw new EnumConstantNotPresentException(ElementsWithText.class, currentElementWithText.name());
        }
    }

    private Cost getCost() {
        Cost result;

        switch (currentElementWithoutText) {
            case PAYROLL:
                result = currentTariff.getPayroll();
                break;
            case CONNECTING_COST:
                result = currentTariff.getConnectingCost();
                break;
            case COST_OF_ONE_MB:
                result = ((InternetTariff) currentTariff).getCostOfOneMB();
                break;
            case CALL_INSIDE_THE_NETWORK:
                result = ((CallingTariff) currentTariff).getCallInsideTheNetwork();
                break;
            case CALL_OUTSIDE_THE_NETWORK:
                result = ((CallingTariff) currentTariff).getCallOutsideTheNetwork();
                break;
            case CALL_TO_FAVOURITE_NUMBER:
                result = ((CallingTariff) currentTariff).getCallToFavoriteNumbers();
                break;
            default:
                throw new EnumConstantNotPresentException(ElementsWithoutText.class, currentElementWithoutText.name());
        }

        return result;
    }
}
