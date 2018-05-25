package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;

@Service
public interface TimeService {
    Long calculateTimeService(Ticket ticket, boolean end);
}
