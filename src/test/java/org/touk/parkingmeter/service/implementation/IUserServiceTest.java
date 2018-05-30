package org.touk.parkingmeter.service.implementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.service.CounterService;
import org.touk.parkingmeter.service.UserService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class IUserServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    private UserService userService;
    private CounterService counterService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new IUserService(ticketRepository, counterService);
    }

    @Test
    public void checkFee() throws ParseException {

        String dateee = "25/05/2018 12:10:25";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = formatter.parse(dateee);

        User user = getUser();
        ParkingMachine parkingMachine = getParkingMachine();
        Ticket ticket = getTicket(parkingMachine, user);
        ticket.setStartDate(d);
        Optional<Ticket> ticketOptional = Optional.of(ticket);

        when(ticketRepository.findById(anyLong())).thenReturn(ticketOptional);

        BigDecimal fee = userService.checkFee(ticket.getId());

        System.out.println("fee:" + fee);


    }


    private ParkingMachine getParkingMachine() {
        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);
        parkingMachine.setLatitude(789L);
        parkingMachine.setLongitude(123L);
        return parkingMachine;
    }


    private User getUser() {
        User user = new User();
        user.setId(5L);
        user.setVip(false);
        user.setPassword("asd");
        user.setEmail("zxc");
        return user;
    }

    private Ticket getTicket(ParkingMachine parkingMachine, User user) {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPlate("LWWL8523");
        ticket.setParkingMachine(parkingMachine);
        ticket.setUser(user);
        return ticket;
    }
}