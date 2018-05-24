package org.touk.parkingmeter.service;

import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.User;

public interface ParkingMachineService {

    boolean startTime(Long longitude, Long latitude, Long userId, String plate);

    double checkFee(User user);

    boolean endTime(ParkingMachine parkingMachine, User user);
}
