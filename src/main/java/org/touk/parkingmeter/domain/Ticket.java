package org.touk.parkingmeter.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date startDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date endDate;

    private String plate;

    @OneToOne(mappedBy = "ticket")
    private User user;

    public abstract double getPrice();

}
