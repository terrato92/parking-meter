package org.touk.parkingmeter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.UserRepository;

public class ICounterServiceRegular implements CounterService {

    @Autowired
    private final UserRepository userRepository;

    public ICounterServiceRegular(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public double parkingRates(User user, Long timeAtParking) {

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

    @Override
    public double currentPrice(User user, Long timeAtParking) {
        return 0;
    }
}
