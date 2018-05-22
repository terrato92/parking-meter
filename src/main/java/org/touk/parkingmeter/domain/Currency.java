package org.touk.parkingmeter.domain;


public abstract class Currency {
    String description = "Unknown currency";

    public String getCurrencyDescription() {
        return description;
    }

    public abstract double cost(double value);

}


// Concrete Component

class PLN extends Currency {
    double value;

    public PLN() {
        description = "Polish Zloty";
    }

    public double cost(double v) {
        value = v;
        return value;
    }

}

