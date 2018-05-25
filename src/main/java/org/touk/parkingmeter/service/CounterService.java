package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;


public interface CounterService {

    double parkingRates(Long timeAtParking);
}
