package com.epam.tariffs.parsing.data.parsers.sax;

import com.epam.tariffs.parsing.model.tariff.Cost;
import com.epam.tariffs.parsing.util.EnumSearcher;
import com.epam.tariffs.parsing.model.tariff.Operator;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.model.tariff.call.CallingTariff;
import com.epam.tariffs.parsing.model.tariff.internet.InternetTariff;
import com.epam.tariffs.parsing.model.tariff.internet.SpeedType;
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
            setCallingTariff();
            setAttributes(attributes);
        } else if ("internet-tariff".equals(localName)) {
            setInternetTariff();
            setAttributes(attributes);
        } else {
            setCurrentElementName(localName);
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

    private void setCallingTariff() {
        CallingTariff callingTariff = new CallingTariff();
        callingTariff.setPayroll(new Cost());
        callingTariff.setConnectingCost(new Cost());
        callingTariff.setCallInsideTheNetwork(new Cost());
        callingTariff.setCallOutsideTheNetwork(new Cost());
        callingTariff.setCallToFavoriteNumbers(new Cost());

        currentTariff = callingTariff;
    }

    private void setInternetTariff() {
        InternetTariff internetTariff = new InternetTariff();
        internetTariff.setPayroll(new Cost());
        internetTariff.setConnectingCost(new Cost());
        internetTariff.setCostOfOneMB(new Cost());

        currentTariff = internetTariff;
    }

    private void setCurrentElementName(String localName) {
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
        int currIndex = 0;

        currentTariff.setId(attributes.getValue(currIndex));

        List<Operator> operators = Arrays.asList(Operator.values());

        if (attributes.getLength() == 3) {
            currentTariff.setName(attributes.getValue(++currIndex));

            String attribute = attributes.getValue(++currIndex);
            Operator operator = new EnumSearcher<>(operators).search(attribute);

            currentTariff.setOperator(operator);
        } else if (attributes.getLength() == 2) {
            String attribute = attributes.getValue(++currIndex);

            Operator operator = new EnumSearcher<>(operators).search(attribute);

            if (operator != null) {
                currentTariff.setOperator(operator);
            } else {
                currentTariff.setOperator(Operator.MTS);
                currentTariff.setName(attribute);
            }
        }
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
                setRubles(rubles);

                break;
            case KOPECKS:
                int kopecks = Integer.parseInt(line);
                setKopecks(kopecks);

                break;
            default:
                throw new EnumConstantNotPresentException(ElementsWithText.class, currentElementWithText.name());
        }
    }

    private void setRubles(int rubles) {
        switch (currentElementWithoutText) {
            case PAYROLL:
                currentTariff.getPayroll().setRubles(rubles);
                break;
            case CONNECTING_COST:
                currentTariff.getConnectingCost().setRubles(rubles);
                break;
            case COST_OF_ONE_MB:
                ((InternetTariff) currentTariff).getCostOfOneMB().setRubles(rubles);
                break;
            case CALL_INSIDE_THE_NETWORK:
                ((CallingTariff) currentTariff).getCallInsideTheNetwork().setRubles(rubles);
                break;
            case CALL_OUTSIDE_THE_NETWORK:
                ((CallingTariff) currentTariff).getCallOutsideTheNetwork().setRubles(rubles);
                break;
            case CALL_TO_FAVOURITE_NUMBER:
                ((CallingTariff) currentTariff).getCallToFavoriteNumbers().setRubles(rubles);
                break;
            default:
                throw new EnumConstantNotPresentException(ElementsWithoutText.class, currentElementWithoutText.name());
        }
    }

    private void setKopecks(int kopecks) {
        switch (currentElementWithoutText) {
            case PAYROLL:
                currentTariff.getPayroll().setKopecks(kopecks);
                break;
            case CONNECTING_COST:
                currentTariff.getConnectingCost().setKopecks(kopecks);
                break;
            case COST_OF_ONE_MB:
                ((InternetTariff) currentTariff).getCostOfOneMB().setKopecks(kopecks);
                break;
            case CALL_INSIDE_THE_NETWORK:
                ((CallingTariff) currentTariff).getCallInsideTheNetwork().setKopecks(kopecks);
                break;
            case CALL_OUTSIDE_THE_NETWORK:
                ((CallingTariff) currentTariff).getCallOutsideTheNetwork().setKopecks(kopecks);
                break;
            case CALL_TO_FAVOURITE_NUMBER:
                ((CallingTariff) currentTariff).getCallToFavoriteNumbers().setKopecks(kopecks);
                break;
            default:
                break;
        }
    }
}
