package org.touk.parkingmeter.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table
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

    @Transient
    private BigDecimal parkingFee;

}
