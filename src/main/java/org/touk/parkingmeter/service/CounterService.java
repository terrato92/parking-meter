package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.User;

@Service
public interface CounterService {

    double parkingRates(User user, Long timeAtParking);
}
