package org.touk.parkingmeter.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.CounterService;

public class ICounterServiceVip implements CounterService {

    @Override
    public double parkingRates(Long timeAtParking) {

        long diffHours = timeAtParking / (60 * 60 * 1000) % 24;
        double fee = 0;

        if (diffHours <= 1) {
            fee = 0;
        } else if (1 < diffHours && diffHours <= 2) {
            fee= 1;
        } else if (2 < diffHours) {

            for (int i = 2; i < diffHours; i++) {
                fee = i * 1.2;
            }
        }
        return fee;
    }

}
