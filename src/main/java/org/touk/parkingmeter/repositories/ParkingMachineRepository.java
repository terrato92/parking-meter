package org.touk.parkingmeter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.touk.parkingmeter.domain.ParkingMachine;

@Repository
public interface ParkingMachineRepository extends CrudRepository<ParkingMachine, Long>{
}
