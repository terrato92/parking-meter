package org.touk.parkingmeter.service;


import java.math.BigDecimal;

public interface CounterService {

    BigDecimal parkingRates(Long timeAtParking);
}
