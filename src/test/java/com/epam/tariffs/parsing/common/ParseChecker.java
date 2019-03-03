package com.epam.tariffs.parsing.common;

import com.epam.tariffs.parsing.model.Cost;
import com.epam.tariffs.parsing.model.Operator;
import com.epam.tariffs.parsing.model.Tariff;
import com.epam.tariffs.parsing.model.call.CallingTariff;
import com.epam.tariffs.parsing.model.internet.InternetTariff;
import com.epam.tariffs.parsing.model.internet.SpeedType;
import org.junit.Assert;

import java.util.List;

public class ParseChecker {
    private static final String AAAAAAAB = "AAAAAAAB";
    private static final String AAAAAAAA = "AAAAAAAA";
    private static final String AAAAAAAD = "AAAAAAAD";

    private static final String PENSIONARY = "Пенсионный";
    private static final String SUPER_MAX = "Супер max";
    private static final String SHAKE_1 = "Шейк 1";

    private static final Operator VELCOM = Operator.VELCOM;
    private static final Operator MTS = Operator.MTS;
    private static final Operator LIFE = Operator.LIFE;

    private static final Cost PAYROLL_3_70 = new Cost(3, 70);
    private static final Cost PAYROLL_20_0 = new Cost(20, 0);
    private static final Cost PAYROLL_11_90 = new Cost(11, 90);

    private static final Cost CONNECTING_COST_5_0 = new Cost(5, 0);
    private static final Cost CONNECTING_COST_7_90 = new Cost(7, 90);

    private static final Cost CALL_INSIDE_THE_NETWORK_0_5 = new Cost(0, 5);
    private static final Cost CALL_INSIDE_THE_NETWORK_0_0 = new Cost(0, 0);

    private static final Cost CALL_OUTSIDE_THE_NETWORK_0_17 = new Cost(0, 17);
    private static final Cost CALL_OUTSIDE_THE_NETWORK_0_0 = new Cost(0, 0);

    private static final Cost CALL_TO_FAVOURITE_NUMBER_0_2 = new Cost(0, 2);
    private static final Cost CALL_TO_FAVOURITE_NUMBER_0_0 = new Cost(0, 0);

    private static final int FAVORITE_NUMBERS_COUNT_5 = 5;
    private static final int FAVORITE_NUMBERS_COUNT_0 = 0;

    private static final int STARTING_NUMBER_OF_MB = 4000;

    private static final Cost COST_OF_ONE_MB = new Cost(0, 0);

    private static final SpeedType LIMITED = SpeedType.LIMITED;

    private static final int ONE_ELEMENT = 1;
    private static final int FIRST = 0;

    public void checkCallingTariffAllAreFilled(List<Tariff> tariffs) {
        Assert.assertEquals(ONE_ELEMENT, tariffs.size());

        Tariff tariff = tariffs.get(FIRST);

        Assert.assertEquals(CallingTariff.class, tariff.getClass());

        CallingTariff callingTariff = (CallingTariff) tariff;

        Assert.assertEquals(AAAAAAAB, callingTariff.getId());
        Assert.assertEquals(PENSIONARY, callingTariff.getName());
        Assert.assertEquals(VELCOM, callingTariff.getOperator());
        Assert.assertEquals(PAYROLL_3_70, callingTariff.getPayroll());
        Assert.assertEquals(CONNECTING_COST_5_0, callingTariff.getConnectingCost());
        Assert.assertEquals(CALL_INSIDE_THE_NETWORK_0_5, callingTariff.getCallInsideTheNetwork());
        Assert.assertEquals(CALL_OUTSIDE_THE_NETWORK_0_17, callingTariff.getCallOutsideTheNetwork());
        Assert.assertEquals(CALL_TO_FAVOURITE_NUMBER_0_2, callingTariff.getCallToFavoriteNumbers());
        Assert.assertEquals(FAVORITE_NUMBERS_COUNT_5, callingTariff.getFavoriteNumbersCount());
    }

    public void checkCallingTariffDefaultOperator(List<Tariff> tariffs) {
        Assert.assertEquals(ONE_ELEMENT, tariffs.size());

        Tariff tariff = tariffs.get(FIRST);

        Assert.assertEquals(CallingTariff.class, tariff.getClass());

        CallingTariff callingTariff = (CallingTariff) tariff;

        Assert.assertEquals(AAAAAAAA, callingTariff.getId());
        Assert.assertEquals(SUPER_MAX, callingTariff.getName());
        Assert.assertEquals(MTS, callingTariff.getOperator());
        Assert.assertEquals(PAYROLL_20_0, callingTariff.getPayroll());
        Assert.assertEquals(CONNECTING_COST_5_0, callingTariff.getConnectingCost());
        Assert.assertEquals(CALL_INSIDE_THE_NETWORK_0_0, callingTariff.getCallInsideTheNetwork());
        Assert.assertEquals(CALL_OUTSIDE_THE_NETWORK_0_0, callingTariff.getCallOutsideTheNetwork());
        Assert.assertEquals(CALL_TO_FAVOURITE_NUMBER_0_0, callingTariff.getCallToFavoriteNumbers());
        Assert.assertEquals(FAVORITE_NUMBERS_COUNT_0, callingTariff.getFavoriteNumbersCount());
    }

    public void checkInternetTariffAllAreFilled(List<Tariff> tariffs) {
        Assert.assertEquals(ONE_ELEMENT, tariffs.size());

        Tariff tariff = tariffs.get(FIRST);

        Assert.assertEquals(InternetTariff.class, tariff.getClass());

        InternetTariff internetTariff = (InternetTariff) tariff;

        Assert.assertEquals(AAAAAAAD, internetTariff.getId());
        Assert.assertEquals(SHAKE_1, internetTariff.getName());
        Assert.assertEquals(LIFE, internetTariff.getOperator());
        Assert.assertEquals(PAYROLL_11_90, internetTariff.getPayroll());
        Assert.assertEquals(CONNECTING_COST_7_90, internetTariff.getConnectingCost());
        Assert.assertEquals(STARTING_NUMBER_OF_MB, internetTariff.getStartingNumberOfMB());
        Assert.assertEquals(COST_OF_ONE_MB, internetTariff.getCostOfOneMB());
        Assert.assertEquals(LIMITED, internetTariff.getSpeedType());
    }
}
