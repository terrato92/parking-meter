package org.touk.parkingmeter.service.implementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.touk.parkingmeter.service.CounterService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ICounterServiceRegularTest {



    @Mock
    private CounterService counterService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        counterService = new ICounterServiceRegular();
    }

    @Test
    public void parkingRates() throws Exception {
        Long time = 7524000L;

        BigDecimal fee = counterService.parkingRates(time);

        assertEquals(0, Double.compare(fee.doubleValue(), 7.5));
    }
}