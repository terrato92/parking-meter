package org.touk.parkingmeter.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.exception.ParkingNotFoundException;
import org.touk.parkingmeter.exception.TicketNotFoundException;
import org.touk.parkingmeter.exception.UserNotFoundException;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.CounterService;
import org.touk.parkingmeter.service.ParkingMachineService;
import org.touk.parkingmeter.service.TimeService;
import java.util.Optional;

@Service
public class IParkingMachineService implements ParkingMachineService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final ParkingMachineRepository parkingMachineRepository;

    private TimeService timeService;

    private CounterService counterService;



    public IParkingMachineService(UserRepository userRepository, TicketRepository ticketRepository, ParkingMachineRepository parkingMachineRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.parkingMachineRepository = parkingMachineRepository;
        timeService = new ITimeService();

    }

    @Override
    public Ticket createTicket(Long longitude, Long latitude, Long userId, String plate) {

        Optional<ParkingMachine> parkingMachineOptional = parkingMachineRepository.findByLongitudeAndLatitude(longitude, latitude);

        if (!parkingMachineOptional.isPresent()) {
            throw new ParkingNotFoundException("Error couldn't find park machine");
        } else {

            Optional<User> userOptional = userRepository.findById(userId);

            if (!userOptional.isPresent()) {
                throw new UserNotFoundException("Error couldn't find user.");
            } else {

                User user = userOptional.get();
                ParkingMachine parkingMachine = parkingMachineOptional.get();

                Ticket ticket = new Ticket();
                ticket.setPlate(plate);
                ticket.setStartDate();
                ticket.setParkingMachine(parkingMachine);
                ticket.setUser(user);

                ticketRepository.save(ticket);

                return ticket;
            }
        }
    }

    @Override
    public Ticket endTicket(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (!ticketOptional.isPresent()) {
            throw new TicketNotFoundException("Couldn't find park machine");
        } else {
            Ticket ticket = ticketOptional.get();
            ticket.setEndDate();

            Long timeAtTheParking = timeService.calculateTimeService(ticket);
            if (ticket.getUser().isVip()){
                counterService = new ICounterServiceVip();
            } else {
                counterService = new ICounterServiceRegular();
            }

            double fee = counterService.parkingRates(timeAtTheParking);

            System.out.println("FEE: " + fee);

            User user = ticket.getUser();
            user.setParkingFee(fee);

            return ticket;
        }
    }

}















