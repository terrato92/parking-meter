package org.touk.parkingmeter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.touk.parkingmeter.converter.ConvertToFeeDto;
import org.touk.parkingmeter.domain.Currency;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.dto.DataDto;
import org.touk.parkingmeter.dto.FeeDto;
import org.touk.parkingmeter.service.ParkingMachineService;
import org.touk.parkingmeter.service.UserService;

import java.math.BigDecimal;

@RestController
public class ParkingController {
    private final ParkingMachineService parkingMachineService;
    private final UserService userService;
    private final ConvertToFeeDto convertToFeeDto;

    @Autowired
    public ParkingController(
            ParkingMachineService parkingMachineService,
            UserService userService, ConvertToFeeDto convertToFeeDto) {

        this.parkingMachineService = parkingMachineService;
        this.userService = userService;
        this.convertToFeeDto = convertToFeeDto;
    }


    @PostMapping("/parking")
    public ResponseEntity<Ticket> addTicket(@RequestBody DataDto data){
        Ticket ticket = parkingMachineService.createTicket(data);

        return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
    }



    @PutMapping("ticket/{ticketId}")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long ticketId) {

        Ticket ticket = parkingMachineService.endTicket(ticketId);

        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @GetMapping("ticket/{ticketId}")
    public ResponseEntity<FeeDto> checkFee(@PathVariable Long ticketId) {

        BigDecimal fee = userService.checkFee(ticketId);

        FeeDto feeDto = convertToFeeDto.convertToFeeDto(Currency.PLN, fee);

        return new ResponseEntity<>(feeDto, HttpStatus.OK);
    }
}
