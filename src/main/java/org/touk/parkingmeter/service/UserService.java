package org.touk.parkingmeter.service;

import java.math.BigDecimal;

public interface UserService {

    BigDecimal checkFee(Long ticketId);
}
