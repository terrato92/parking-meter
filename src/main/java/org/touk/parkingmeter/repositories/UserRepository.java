package org.touk.parkingmeter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.touk.parkingmeter.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
