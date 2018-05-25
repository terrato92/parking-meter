package org.touk.parkingmeter.service;

import org.touk.parkingmeter.domain.Ticket;

public interface ParkingMachineService {

    Ticket createTicket(Long longitude, Long latitude, Long userId, String plate);

    Ticket endTicket(Long ticketId);
}
