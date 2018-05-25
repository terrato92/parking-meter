package org.touk.parkingmeter.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId){
        super("Couldn't find user with id: " + userId);
    }
}
