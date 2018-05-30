package org.touk.parkingmeter.dto;

import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;

/**
 * Created by onenight on 2018-05-29.
 */
public class ConvertToDto {

    public DataDto convertToDto(ParkingMachine parkingMachine,
                                 User user,
                                 Ticket ticket) {

        DataDto dataDto = new DataDto();
        dataDto.setLatitude(parkingMachine.getLatitude());
        dataDto.setLongitude(parkingMachine.getLongitude());
        dataDto.setPlate(ticket.getPlate());
        dataDto.setUserId(user.getId());

        return dataDto;
    }
}
