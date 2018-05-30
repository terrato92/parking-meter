package org.touk.parkingmeter.service.implementation;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.service.TimeService;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

@Service
public class ITimeService implements TimeService {
    @Override
    public Long calculateTimeService(Ticket ticket) {

        Date startDate = ticket.getStartDate();
        Date endDate;

        if (ticket.getEndDate() == null) {

            Instant nowTime = Instant.now();
            endDate = Date.from(nowTime);

        } else {
            endDate = ticket.getEndDate();
        }

        long diff = 0L;

        try {
            //in milliseconds
            diff = endDate.getTime() - startDate.getTime();

            System.out.printf("Arriving at:  %s %n", startDate);

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            // todo to do logow

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            System.out.println("\nDeparture time: " + ticket.getEndDate());

        } catch (DateTimeException ex) {
            ex.printStackTrace();
        }

        return diff;
    }
}
