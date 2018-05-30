package org.touk.parkingmeter.service.implementation;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.touk.parkingmeter.service.CounterService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Primary
@Service("regular")
public class ICounterServiceRegular implements CounterService {

    @Override
    public BigDecimal parkingRates(Long timeAtParking) {

        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeAtParking);

        BigDecimal fee = new BigDecimal("0");

        if (minutes <= 60) {
            fee = new BigDecimal("1");
        }

        if (60 < minutes && minutes <= 120) {
            fee = fee.add(new BigDecimal(1));
        }

        if (minutes > 120) {
            fee = fee.add(BigDecimal.valueOf((minutes - 120)/60)).multiply(BigDecimal.valueOf(1.5));
        }

        return fee;
    }
}