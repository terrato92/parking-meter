package org.touk.parkingmeter.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.service.CounterService;
import org.touk.parkingmeter.service.TimeService;
import org.touk.parkingmeter.service.UserService;

import java.util.Optional;

@Service
public class IUserService implements UserService {

    @Autowired
    private final TicketRepository ticketRepository;

    private CounterService counterService;

    private TimeService timeService;

    public IUserService(TicketRepository ticketRepository, CounterService counterService) {
        this.ticketRepository = ticketRepository;
        this.counterService = counterService;
        timeService = new ITimeService();
    }

    @Override
    public double checkFee(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();

            Long timeAtTheParking = timeService.calculateTimeService(ticket);
            User user = ticket.getUser();
            double price = 0;


            if (user.isVip()) {

                counterService = new ICounterServiceVip();
                price = counterService.parkingRates(timeAtTheParking);
            } else if (!user.isVip()) {

                counterService = new ICounterServiceRegular();
                price = counterService.parkingRates(timeAtTheParking);
            }

            return price;

        } else {
            throw new RuntimeException("Couldn't find park machine.");
        }
    }

}
