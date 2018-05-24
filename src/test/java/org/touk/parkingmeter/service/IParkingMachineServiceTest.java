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
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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

        when(parkingMachineRepository.findParkingMachineByPoints(anyLong(), anyLong())).thenReturn(parkingMachineOptional);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(ticketRepository.findById(anyLong())).thenReturn(ticketOptional);

        Ticket newTicket = iParkingMachineService.createTicket(parkingMachine.getLongitude(), parkingMachine.getLatitude(), ticket.getUser().getId(), ticket.getPlate());

        assertNotNull(newTicket.getStartDate());
        assertEquals(parkingMachine.getTickets().size(), 1);

    }

    private User getUser() {
        User user = new User();
        user.setId(5L);
        user.setVip(true);
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


//    @Test
//    public void endTime() throws ParseException {
//        User user = getUser();
//        Optional<User> userOptional = Optional.of(user);
//
//        ParkingMachine parkingMachine = new ParkingMachine();
//        parkingMachine.setId(2L);
//        parkingMachine.addTicket(user.getTicket());
//        Optional<ParkingMachine> parkingMachineOptional = Optional.of(parkingMachine);
//
//        when(parkingMachineRepository.findById(anyLong())).thenReturn(parkingMachineOptional);
//        when(userRepository.findById(anyLong())).thenReturn(userOptional);
//
//        assertTrue(iParkingMachineService.endTime(parkingMachine, user));
//        assertNotNull(user.getTicket().getEndDate());
//    }
//
//    private User getUser() throws ParseException {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("okm@nj.pl");
//        user.setPassword("po");
//        user.setVip(false);
//
//        String dateee = "24/05/2018 16:10:25";
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//
//        Date d = formatter.parse(dateee);
//
//        user.getTicket().setId(1L);
//        user.getTicket().setStartDate(d);
//        user.getTicket().setPlate("lwd2345");
//
//        return user;
//    }
}