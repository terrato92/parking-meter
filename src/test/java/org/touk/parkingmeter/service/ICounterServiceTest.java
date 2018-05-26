package org.touk.parkingmeter.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.implementation.IParkingMachineService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ICounterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ParkingMachineRepository parkingMachineRepository;

    @Mock
    private TicketRepository ticketRepository;

    IParkingMachineService iParkingMachineService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        iParkingMachineService = new IParkingMachineService(userRepository, ticketRepository, parkingMachineRepository);
    }

    @Test
    public void parkingRates() throws ParseException {

    }


    private User getUser() throws ParseException {
        User user = new User();
        user.setId(1L);
        user.setEmail("okm@nj.pl");
        user.setPassword("po");
        user.setVip(false);

        String dateee = "24/05/2018 16:10:25";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d = formatter.parse(dateee);


        return user;
    }
}