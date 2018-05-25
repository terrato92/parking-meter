package org.touk.parkingmeter.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String ticketId){
        super("Couldn't find ticket with id: " + ticketId);
    }
}
