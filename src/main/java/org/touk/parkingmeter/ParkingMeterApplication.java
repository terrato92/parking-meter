package org.touk.parkingmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan
@ComponentScan
public class ParkingMeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingMeterApplication.class, args);
	}
}
