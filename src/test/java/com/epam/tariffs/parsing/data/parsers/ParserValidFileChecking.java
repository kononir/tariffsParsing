package com.epam.tariffs.parsing.data.parsers;

import com.epam.tariffs.parsing.model.tariff.Cost;
import com.epam.tariffs.parsing.model.tariff.Operator;
import com.epam.tariffs.parsing.model.tariff.Tariff;
import com.epam.tariffs.parsing.model.tariff.call.CallingTariff;
import org.junit.Assert;

import java.util.List;

public class ParserValidFileChecking {
    private static final String ID = "AAAAAAAA";
    private static final String NAME = "Супер max";
    private static final Operator OPERATOR = Operator.MTS;
    private static final Cost PAYROLL = new Cost(20, 0);
    private static final Cost CONNECTING_COST = new Cost(5, 0);
    private static final Cost CALL_COSTS = new Cost(0, 0);
    private static final int FAVORITE_NUMBERS_COUNT = 0;

    private static final int ONE_ELEMENT = 1;
    private static final int FIRST = 0;

    public void check(List<Tariff> tariffs) {
        Assert.assertEquals(ONE_ELEMENT, tariffs.size());

        Tariff tariff = tariffs.get(FIRST);

        Assert.assertEquals(CallingTariff.class, tariff.getClass());

        CallingTariff callingTariff = (CallingTariff) tariff;

        Assert.assertEquals(ID, callingTariff.getId());
        Assert.assertEquals(NAME, callingTariff.getName());
        Assert.assertEquals(OPERATOR, callingTariff.getOperator());
        Assert.assertEquals(PAYROLL, callingTariff.getPayroll());
        Assert.assertEquals(CONNECTING_COST, callingTariff.getConnectingCost());
        Assert.assertEquals(CALL_COSTS, callingTariff.getCallInsideTheNetwork());
        Assert.assertEquals(CALL_COSTS, callingTariff.getCallOutsideTheNetwork());
        Assert.assertEquals(CALL_COSTS, callingTariff.getCallToFavoriteNumbers());
        Assert.assertEquals(FAVORITE_NUMBERS_COUNT, callingTariff.getFavoriteNumbersCount());
    }
}
