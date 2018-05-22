package org.touk.parkingmeter.domain;

public class Regular extends Ticket {

    @Override
    public double getPrice() {
        return 0;
    }
}
