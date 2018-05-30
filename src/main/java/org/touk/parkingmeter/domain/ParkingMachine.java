package org.touk.parkingmeter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class ParkingMachine {

    @Id
    @GeneratedValue
    private Long id;

    private Long longitude;
    private Long latitude;

}
