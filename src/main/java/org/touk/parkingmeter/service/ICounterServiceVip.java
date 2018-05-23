package org.touk.parkingmeter.service;

import java.util.Date;

public class ICounterServiceVip implements CounterService {




    @Override
    public double parkingRates(Date startTime, Date endTime) {
        return 0;
    }

    @Override
    public double currentPrice(Date startTime) {
        return 0;
    }
}
