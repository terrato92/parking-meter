package org.touk.parkingmeter.service.implementation;

import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.service.CounterService;

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
