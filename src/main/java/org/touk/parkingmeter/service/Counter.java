package org.touk.parkingmeter.service;

import java.util.Date;

public interface Counter {

    double parkingRates(Date startTime, Date endTime);
    double currentPrice(Date startTime);
}
