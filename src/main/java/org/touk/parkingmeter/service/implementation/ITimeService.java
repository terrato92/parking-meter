package org.touk.parkingmeter.service.implementation;

import org.springframework.stereotype.Service;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.service.TimeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class ITimeService implements TimeService {

    @Override
    public Long calculateTimeService(Ticket ticket) {

        Date start = ticket.getStartDate();
        Date d2 = null;

        String checking = null;

        LocalDateTime arrivalDate = LocalDateTime.now();
        try {

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            checking = arrivalDate.format(format);
            System.out.printf("Arriving at:  %s %n", start);

            d2 = formatter.parse(checking);

            //in milliseconds
            long diff = d2.getTime() - start.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            System.out.println("\nActual time: " + d2);

        } catch (DateTimeException ex) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d2.getTime() - start.getTime();
    }
}
