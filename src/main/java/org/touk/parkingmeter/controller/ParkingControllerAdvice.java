package org.touk.parkingmeter.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.touk.parkingmeter.exception.ParkingNotFoundException;
import org.touk.parkingmeter.exception.TicketNotFoundException;
import org.touk.parkingmeter.exception.UserNotFoundException;

@ControllerAdvice
public class ParkingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors userNotFoundExceptionHandler(UserNotFoundException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ParkingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors parkingNotFoundExceptionHandler(ParkingNotFoundException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors ticketNotFoundExceptionHandler(TicketNotFoundException ex) {
        return new VndErrors("error", ex.getMessage());
    }
}
