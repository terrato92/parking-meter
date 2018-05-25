package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;

@Service
public interface ParkingMachineService {

    Ticket createTicket(Long longitude, Long latitude, Long userId, String plate);

    Ticket endTicket(Long ticketId);
}
