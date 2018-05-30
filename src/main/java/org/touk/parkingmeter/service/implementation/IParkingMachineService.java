package org.touk.parkingmeter.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Currency;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.dto.DataDto;
import org.touk.parkingmeter.exception.ResourceNotFoundException;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.CounterService;
import org.touk.parkingmeter.service.ParkingMachineService;
import org.touk.parkingmeter.service.TimeService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IParkingMachineService implements ParkingMachineService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final ParkingMachineRepository parkingMachineRepository;

    @Autowired
    private TimeService timeService;


    public IParkingMachineService(UserRepository userRepository,
                                  TicketRepository ticketRepository,
                                  ParkingMachineRepository parkingMachineRepository,
                                  TimeService timeService) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.parkingMachineRepository = parkingMachineRepository;
        this.timeService = timeService;

    }

    @Override
    public Ticket createTicket(DataDto dataDto) {
        Optional<ParkingMachine> parkingMachineOptional = parkingMachineRepository.findByLongitudeAndLatitude(dataDto.getLongitude(), dataDto.getLatitude());

        if (!parkingMachineOptional.isPresent()) {
            throw new ResourceNotFoundException();
        } else {

            Optional<User> userOptional = userRepository.findById(dataDto.getUserId());

            if (!userOptional.isPresent()) {
                throw new ResourceNotFoundException();
            } else {

                User user = userOptional.get();
                ParkingMachine parkingMachine = parkingMachineOptional.get();

                Ticket ticket = new Ticket();
                ticket.setPlate(dataDto.getPlate());
                ticket.setStartDate();
                ticket.setParkingMachine(parkingMachine);
                ticket.setUser(user);
                ticket.setCurrency(Currency.PLN);

                ticketRepository.save(ticket);

                return ticket;
            }
        }
    }

    @Override
    public Ticket endTicket(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (!ticketOptional.isPresent()) {
            throw new ResourceNotFoundException();
        } else {
            Ticket ticket = ticketOptional.get();
            ticket.setEndDate();

            ticketRepository.save(ticket);

            Long timeAtTheParking = timeService.calculateTimeService(ticket);
            CounterService counterService;
            if (ticket.getUser().isVip()){
                counterService = new ICounterServiceVip();
            } else {
                counterService = new ICounterServiceRegular();
            }

            BigDecimal fee = counterService.parkingRates(timeAtTheParking);

            System.out.println("FEE: " + fee);

            User user = ticket.getUser();
            user.setParkingFee(fee);


            return ticket;
        }
    }

}















