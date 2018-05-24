package org.touk.parkingmeter.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    @NotNull
    private Date startDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date endDate;

    private String plate;

    @ManyToOne
    private ParkingMachine parkingMachine;


    public void setStartDate(){
        Instant nowTime = Instant.now();
        startDate = Date.from(nowTime);
    }

    public void endDate(){
        Instant nowTime = Instant.now();
        startDate = Date.from(nowTime);
    }

}
