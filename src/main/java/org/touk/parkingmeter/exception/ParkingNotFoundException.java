package org.touk.parkingmeter.exception;

public class ParkingNotFoundException extends RuntimeException {

    public ParkingNotFoundException(String parkingId){
        super("Couldn't find user with id:" + parkingId);
    }
}
