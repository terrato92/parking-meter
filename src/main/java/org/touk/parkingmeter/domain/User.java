package org.touk.parkingmeter.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Client")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @NotNull
    private String email;
    @NotNull
    private String password;

    private boolean vip;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

}
