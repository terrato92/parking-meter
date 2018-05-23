package org.touk.parkingmeter.service;

import java.util.Date;

public class ICounterServiceRegular implements CounterService {

    @Override
    public double parkingRates(Long timeAtParking) {
        return 0;
    }

    @Override
    public double currentPrice(Long timeAtParking) {
        return 0;
    }
}
