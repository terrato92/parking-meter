package org.touk.parkingmeter.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;

    private String plate;

    @ManyToOne
    private ParkingMachine parkingMachine;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public void setStartDate() {
        Instant nowTime = Instant.now();
        startDate = Date.from(nowTime);
    }

    public void setEndDate() {
        Instant nowTime = Instant.now();
        endDate = Date.from(nowTime);
    }
}
