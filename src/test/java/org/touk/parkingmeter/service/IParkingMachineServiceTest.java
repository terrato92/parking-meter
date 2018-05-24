package org.touk.parkingmeter.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.implementation.IParkingMachineService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class IParkingMachineServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    ParkingMachineRepository parkingMachineRepository;

    IParkingMachineService iParkingMachine;

    private CounterService counterService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        iParkingMachine = new IParkingMachineService(userRepository, parkingMachineRepository);

    }

    @Test
    public void startTime() throws ParseException {
        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);

        User user = getUser();

        Optional<User> userOptional = Optional.of(user);
        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);

        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(parkingMachineRepository.findById(anyLong())).thenReturn(parkingMachineOptional);

        boolean start = iParkingMachine.startTime(parkingMachine, user, user.getTicket().getPlate());

        assertEquals(parkingMachine.getUsers().size(), 1);
        assertTrue(start);
    }


    @Test
    public void check() throws ParseException {
        User user = getUser();
        Optional<User> userOptional = Optional.of(user);

        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);
        parkingMachine.addUser(user);
        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);

        when(parkingMachineRepository.findById(anyLong())).thenReturn(parkingMachineOptional);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        double fee = iParkingMachine.checkFee(user);

        assertEquals(0, Double.compare(1, fee));
    }

    @Test
    public void endTime() {

    }

    private Date getDate() {
        Instant timestamp = Instant.now();
        return Date.from(timestamp);
    }

    private User getUser() throws ParseException {
        User user = new User();
        user.setId(1L);
        user.setEmail("okm@nj.pl");
        user.setPassword("po");
        user.setVip(true);

        String dateee = "24/05/2018 16:23:25";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d = formatter.parse(dateee);

        Ticket ticket = user.getTicket();
        ticket.setStartDate(d);
        ticket.setPlate("lwd2345");
        user.setTicket(ticket);

        return user;
    }
}