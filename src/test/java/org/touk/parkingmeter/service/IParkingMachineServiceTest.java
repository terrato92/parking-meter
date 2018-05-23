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
    public void startTime() {
        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);

        User user = new User();
        user.setId(1L);
        user.setEmail("okm@nj.pl");
        user.setPassword("po");
        user.setVip(true);

        Instant timestamp = Instant.now();
        Date startTime = Date.from(timestamp);

        Ticket ticket = user.getTicket();
        ticket.setStartDate(startTime);
        ticket.setPlate("lwd2345");

        Optional<User> userOptional = Optional.of(user);
        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);

        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        when(parkingMachineRepository.findById(anyLong())).thenReturn(parkingMachineOptional);

        boolean start = iParkingMachine.startTime(parkingMachine, user, user.getTicket().getPlate());

        assertEquals(parkingMachine.getUsers().size(), 1);
        assertTrue(start);
    }

    @Test
    public void check() {
    }

    @Test
    public void endTime() {
    }
}