package org.touk.parkingmeter.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class ParkingMachine {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    Set<User> users = new HashSet<>();

    public void addUser(User user){
        users.add(user);
    }

}
