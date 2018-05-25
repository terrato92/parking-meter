package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    double checkFee(Long ticketId);
}
