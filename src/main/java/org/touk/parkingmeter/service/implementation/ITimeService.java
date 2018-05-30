package org.touk.parkingmeter.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.service.TimeService;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

@Service
public class ITimeService implements TimeService {

    private static final Logger log = LoggerFactory.getLogger(ITimeService.class);

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

            log.info("Days: {}", diffDays);
            log.info("Hours: {}", diffHours);
            log.info("Minutes: {}", diffMinutes);
            log.info("Seconds: {}", diffSeconds);

        } catch (DateTimeException ex) {
            ex.printStackTrace();
        }

        return diff;
    }
}
