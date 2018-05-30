package org.touk.parkingmeter.service;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.dto.DataDto;

@Service
public interface ParkingMachineService {


    Ticket createTicket(DataDto dataDto);

    Ticket endTicket(Long ticketId);
}
