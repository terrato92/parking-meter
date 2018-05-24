package org.touk.parkingmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.UserRepository;

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
    private final ParkingMachineRepository parkingMachineRepository;

    private CounterService counterService;


    public IParkingMachineService(UserRepository userRepository, ParkingMachineRepository parkingMachineRepository) {
        this.userRepository = userRepository;
        this.parkingMachineRepository = parkingMachineRepository;
    }

    @Override
    public boolean startTime(ParkingMachine parkingMachine, User user, String plate) {

        Optional<ParkingMachine> parkingMachineOptional = parkingMachineRepository.findById(parkingMachine.getId());

        if (!parkingMachineOptional.isPresent()) {
            return false;
        } else {

            Optional<User> userOptional = userRepository.findById(user.getId());

            if (!userOptional.isPresent()) {
                return false;
            } else {

                ParkingMachine parkingMachine1 = parkingMachineOptional.get();

                User client = userOptional.get();
                client.getTicket().setPlate(plate);
                client.getTicket().setStartDate();
                parkingMachine.addUser(client);

                userRepository.save(user);
                parkingMachineRepository.save(parkingMachine1);
                return true;
            }
        }
    }

    @Override
    public double check(User user) {

        Optional<User> userOptional = Optional.of(user);

        if (userOptional.isPresent()) {

            Long timeAtTheParking = calculateTimeStop(user);

            double price = 0;

            if (user.isVip()) {

                counterService = new ICounterServiceVip();
                price = counterService.currentPrice(user, timeAtTheParking);
            } else if (!user.isVip()) {

                counterService = new ICounterServiceRegular(userRepository);
                price = counterService.parkingRates(user, timeAtTheParking);

            }

            return price;

        } else {
            throw new NullPointerException("NULL");
        }

    }

    @Override
    public boolean endTime(User user) {
        return false;
    }


    private Long calculateTimeStop(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isPresent()) {
            User client = userOptional.get();
            Date start = client.getTicket().getStartDate();
            Date d2 = null;

            String checking = null;

            LocalDateTime arrivalDate = LocalDateTime.now();
            try {

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                checking = arrivalDate.format(format);
                System.out.printf("Arriving at :  %s %n", checking);

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


            return d2.getTime() - start.getTime();

        } else {
            throw new NullPointerException("NULL");
        }
    }
}















