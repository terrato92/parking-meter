package org.touk.parkingmeter.service;

import org.touk.parkingmeter.domain.User;

public class ICounterServiceVip implements CounterService {

    @Override
    public double parkingRates(User user, Long timeAtParking) {

        return 0;
    }

    @Override
    public double currentPrice(User user, Long timeAtParking) {
        return 0;
    }
}
