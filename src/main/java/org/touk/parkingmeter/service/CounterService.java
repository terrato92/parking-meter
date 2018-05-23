package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface CounterService {

    double parkingRates(Date startTime, Date endTime);
    double currentPrice(Date startTime);
}
