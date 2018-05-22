package org.touk.parkingmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan
public class ParkingMeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingMeterApplication.class, args);
	}
}
