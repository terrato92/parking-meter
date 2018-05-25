package org.touk.parkingmeter.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
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

public class IParkingMachineServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ParkingMachineRepository parkingMachineRepository;

    @Mock
    private TicketRepository ticketRepository;

    private IParkingMachineService iParkingMachineService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        iParkingMachineService = new IParkingMachineService(userRepository, ticketRepository, parkingMachineRepository);
    }


    @Test
    public void createTicket() {
        ParkingMachine parkingMachine = getParkingMachine();
        User user = getUser();
        Ticket ticket = getTicket(parkingMachine, user);

        parkingMachine.addTicket(ticket); // check debbugiem

        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);
        Optional<User> userOptional = Optional.of(user);
        Optional<Ticket> ticketOptional = Optional.of(ticket);

        when(parkingMachineRepository.findByLongitudeAndLatitude(anyLong(), anyLong())).thenReturn(parkingMachineOptional);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(ticketRepository.findById(anyLong())).thenReturn(ticketOptional);

        Ticket newTicket = iParkingMachineService.createTicket(parkingMachine.getLongitude(), parkingMachine.getLatitude(), ticket.getUser().getId(), ticket.getPlate());

        assertNotNull(newTicket.getStartDate());
        assertEquals(parkingMachine.getTickets().size(), 1);
    }

    @Test
    public void endTime() throws ParseException {
        String dateee = "25/05/2018 00:10:25";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = formatter.parse(dateee);

        ParkingMachine parkingMachine = getParkingMachine();
        User user = getUser();
        Ticket ticket = getTicket(parkingMachine, user);
        ticket.setStartDate(d);

        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);
        Optional<User> userOptional = Optional.of(user);
        Optional<Ticket> ticketOptional = Optional.of(ticket);

        when(parkingMachineRepository.findByLongitudeAndLatitude(anyLong(), anyLong())).thenReturn(parkingMachineOptional);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(ticketRepository.findById(anyLong())).thenReturn(ticketOptional);

        double toPay = iParkingMachineService.endTime(ticket.getId());

        System.out.println(toPay);

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

    private ParkingMachine getParkingMachine() {
        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);
        parkingMachine.setLatitude(789L);
        parkingMachine.setLongitude(123L);
        return parkingMachine;
    }
}