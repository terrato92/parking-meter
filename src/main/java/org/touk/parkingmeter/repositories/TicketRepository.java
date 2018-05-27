package org.touk.parkingmeter.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.touk.parkingmeter.domain.Ticket;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
