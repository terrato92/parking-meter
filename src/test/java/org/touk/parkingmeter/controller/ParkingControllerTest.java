package org.touk.parkingmeter.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;
import org.touk.parkingmeter.service.implementation.IParkingMachineService;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.touk.parkingmeter.controller.AbstractRestControllerTest.asJsonString;

public class ParkingControllerTest {

    @Mock
    private IParkingMachineService iParkingMachineService;

    @InjectMocks
    private ParkingController parkingController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(parkingController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void addTicket() throws Exception {
        ParkingMachine parkingMachine = getParkingMachine();
        User user = getUser();
        Ticket ticket = getTicket(parkingMachine, user);

        when(iParkingMachineService.createTicket(any())).thenReturn(ticket);

        mockMvc.perform(post("/parking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.plate", equalTo("lwwl34r4")));
    }

    @Test
    public void closeTicket() throws Exception {
        ParkingMachine parkingMachine = getParkingMachine();
        User user = getUser();
        Ticket ticket = getTicket(parkingMachine, user);

        when(iParkingMachineService.endTicket(anyLong())).thenReturn(ticket);

        mockMvc.perform(put("/ticket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ticket)))
                .andExpect(status().isOk());
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
        ticket.setPlate("lwwl34r4");
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