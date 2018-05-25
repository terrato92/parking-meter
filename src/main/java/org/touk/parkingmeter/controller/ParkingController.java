package org.touk.parkingmeter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.repositories.ParkingMachineRepository;
import org.touk.parkingmeter.repositories.TicketRepository;
import org.touk.parkingmeter.repositories.UserRepository;
import org.touk.parkingmeter.service.ParkingMachineService;
import org.touk.parkingmeter.service.UserService;

@RestController
public class ParkingController {


    private final ParkingMachineRepository parkingMachineRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    private ParkingMachineService parkingMachineService;

    private UserService userService;

    @Autowired
    public ParkingController(ParkingMachineRepository parkingMachineRepository,
                             TicketRepository ticketRepository,
                             UserRepository userRepository,
                             ParkingMachineService parkingMachineService,
                             UserService userService) {

        this.parkingMachineRepository = parkingMachineRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.parkingMachineService = parkingMachineService;
        this.userService = userService;
    }


    @PostMapping("parking/{longitude}/{latitude}/user/{userId}/plate/{plate}")
    public ResponseEntity<Ticket> addTicket(@PathVariable Long longitude,
                                            @PathVariable Long latitude,
                                            @PathVariable Long userId,
                                            @PathVariable String plate) {

        Ticket ticket = parkingMachineService.createTicket(longitude, latitude, userId, plate);

        return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("ticket/{longId}")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long ticketId){

        Ticket ticket = parkingMachineService.endTicket(ticketId);

        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @GetMapping("ticket/{longId}")
    public ResponseEntity<Double> checkFee(@PathVariable Long ticketId){
        Double fee = userService.checkFee(ticketId);

        return new ResponseEntity<>(fee, HttpStatus.OK);
    }
}