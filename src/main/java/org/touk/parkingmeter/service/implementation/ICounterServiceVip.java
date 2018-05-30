package org.touk.parkingmeter.service.implementation;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.service.CounterService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

@Service("vip")
public class ICounterServiceVip implements CounterService {

    @Override
    public BigDecimal parkingRates(Long timeAtParking) {

        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeAtParking);

        BigDecimal fee = new BigDecimal("0");

        if (minutes <= 60) {
            fee = new BigDecimal("0");
        }

        if (60 < minutes && minutes <= 120) {
            fee = fee.add(new BigDecimal("2"));
        }

        if (minutes > 120) {
            fee = BigDecimal.valueOf(2);
            fee = fee.add(BigDecimal.valueOf(minutes/60).multiply(BigDecimal.valueOf(1.2))).setScale(1,RoundingMode.HALF_UP);
        }

        return fee;
    }
}