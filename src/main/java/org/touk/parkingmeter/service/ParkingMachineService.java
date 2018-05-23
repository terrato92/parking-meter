package org.touk.parkingmeter.service;

import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.User;

import java.util.Date;

public interface ParkingMachineService {


    boolean startTime(ParkingMachine parkingMachine, User user, String plate);

    double check(User user);

    boolean endTime(User user);
}
