package org.touk.parkingmeter.service.implementation;


import org.springframework.stereotype.Service;
import org.touk.parkingmeter.service.CounterService;

@Service
public class ICounterServiceRegular implements CounterService {

    @Override
    public double parkingRates(Long timeAtParking) {

        long diffHours = timeAtParking / (60 * 60 * 1000) % 24;
        double fee = 0;

        if (diffHours <= 1) {
            fee = 1;
        } else if (1 < diffHours && diffHours <= 2) {
            fee= 2;
        } else if (2 < diffHours) {

            for (int i = 2; i < diffHours; i++) {
                fee = i * 1.5;
            }
        }
        return fee;
    }
}
