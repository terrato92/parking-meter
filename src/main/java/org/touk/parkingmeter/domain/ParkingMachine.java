package org.touk.parkingmeter.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ParkingMachine {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    Set<Ticket> tickets = new HashSet<>();

    private Long longitude;
    private Long latitude;

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
