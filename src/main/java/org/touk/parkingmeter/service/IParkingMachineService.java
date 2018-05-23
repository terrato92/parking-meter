package org.touk.parkingmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.UserRepository;

import java.util.Optional;

public class IParkingMachineService implements ParkingMachineService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ParkingMachineRepository parkingMachineRepository;


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
        return 0;
    }

    @Override
    public boolean endTime(User user) {
        return false;
    }
}
