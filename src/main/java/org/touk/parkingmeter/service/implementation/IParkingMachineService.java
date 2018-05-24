package org.touk.parkingmeter.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.CounterService;
import org.touk.parkingmeter.service.ParkingMachineService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class IParkingMachineService implements ParkingMachineService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final ParkingMachineRepository parkingMachineRepository;

    private CounterService counterService;


    public IParkingMachineService(UserRepository userRepository, TicketRepository ticketRepository, ParkingMachineRepository parkingMachineRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.parkingMachineRepository = parkingMachineRepository;
    }

    @Override
    public Ticket createTicket(Long longitude, Long latitude, Long userId, String plate) {

        Optional<ParkingMachine> parkingMachineOptional = parkingMachineRepository.findParkingMachineByPoints(longitude, latitude);

        if (!parkingMachineOptional.isPresent()) {
            throw new RuntimeException("Error couldn't find park machine");
        } else {

            Optional<User> userOptional = userRepository.findById(userId);

            if (!userOptional.isPresent()) {
                throw new RuntimeException("Error couldn't find user.");
            } else {

                ParkingMachine parkingMachine = parkingMachineOptional.get();

                Ticket ticket = new Ticket();
                ticket.setPlate(plate);
                ticket.setStartDate();
                ticket.setParkingMachine(parkingMachine);

                ticketRepository.save(ticket);

                return ticket;
            }
        }
    }

    @Override
    public double checkFee(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();

            Long timeAtTheParking = calculateTimeParking(ticket, false);
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

    @Override
    public double endTime(Long ticketId) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        if (!ticketOptional.isPresent()) {
            throw new RuntimeException("Couldn't find park machine");
        } else {
            Ticket ticket = ticketOptional.get();
            ticket.setEndDate();
            ticket.setPlate(null);

            User user = ticket.getUser();
            user.setParkingFee(checkFee(ticketId));

            Long timeAtTheParking = calculateTimeParking(ticket, true);

            double fee = counterService.parkingRates(timeAtTheParking);
            System.out.println("FEE: " + fee);

            return fee;
        }
    }

    private Long calculateTimeParking(Ticket ticket, boolean end) {

        Date start = ticket.getStartDate();
        Date d2 = null;

        String checking = null;

        LocalDateTime arrivalDate = LocalDateTime.now();
        try {

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            checking = arrivalDate.format(format);
            System.out.printf("Arriving at:  %s %n", start);

            d2 = formatter.parse(checking);

            //in milliseconds
            long diff = d2.getTime() - start.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (end)
            ticket.setEndDate(d2);

        return d2.getTime() - start.getTime();


    }
}















