package org.touk.parkingmeter.service;

import org.touk.parkingmeter.domain.Ticket;

public interface TimeService {
    Long calculateTimeService(Ticket ticket);
}
