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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ICounterServiceRegularTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ParkingMachineRepository parkingMachineRepository;

    CounterService counterService;

    IParkingMachineService iParkingMachineService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        iParkingMachineService = new IParkingMachineService(userRepository, parkingMachineRepository);
    }

    @Test
    public void parkingRates() throws ParseException {
        User user = getUser();
        Optional<User> userOptional = Optional.of(user);

        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);
        parkingMachine.addUser(user);
        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);

        when(parkingMachineRepository.findById(anyLong())).thenReturn(parkingMachineOptional);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        double fee = iParkingMachineService.check(user);

        assertEquals(0, Double.compare(1, fee));

    }

    @Test
    public void currentPrice() {
    }


    private User getUser() throws ParseException {
        User user = new User();
        user.setId(1L);
        user.setEmail("okm@nj.pl");
        user.setPassword("po");
        user.setVip(false);

        String dateee = "24/05/2018 14:10:25";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d = formatter.parse(dateee);

        Ticket ticket = user.getTicket();
        ticket.setId(1L);
        ticket.setStartDate(d);
        ticket.setPlate("lwd2345");
        user.setTicket(ticket);

        return user;
    }
}