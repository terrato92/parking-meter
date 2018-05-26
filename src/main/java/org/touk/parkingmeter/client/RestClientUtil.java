package org.touk.parkingmeter.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.touk.parkingmeter.domain.ParkingMachine;
import org.touk.parkingmeter.domain.Ticket;
import org.touk.parkingmeter.domain.User;

import java.net.URI;

public class RestClientUtil {

    public void addTicket(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:9100/parking/{longitude}/{latitude}/user/{userId}/plate/{plate}";

        ParkingMachine parkingMachine = getParkingMachine();
        User user = getUser();

        Ticket ticket = getTicket(parkingMachine, user);

        HttpEntity<Ticket> requestEntity = new HttpEntity<>(ticket, headers);
        URI uri = template.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    public void checkFee(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:9100/ticket/{longId}";


        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Ticket> responseEntity = template.exchange(url, HttpMethod.POST, requestEntity, Ticket.class, 1);

        Ticket ticket = responseEntity.getBody();
        System.out.println("Id: " + ticket.getId() + ", start date: " + ticket.getEndDate() + ", end date: " + ticket.getEndDate());

    }

    public void endTicket(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9100/ticket/{longId}";

        HttpEntity<Ticket> requestEntity = new HttpEntity<>(headers);
        restTemplate.put(url, HttpMethod.PUT, requestEntity);
    }

    public static void main(String args[]) {
        RestClientUtil util = new RestClientUtil();

//        util.addTicket();
        util.checkFee();

    }

    private User getUser() {
        User user = new User();
        user.setId(5L);
        user.setVip(false);
        user.setPassword("asd");
        user.setEmail("zxc");
        return user;
    }

    private Ticket getTicket(ParkingMachine parkingMachine, User user) {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPlate("LWWL8523");
        ticket.setParkingMachine(parkingMachine);
        ticket.setUser(user);
        return ticket;
    }

    private ParkingMachine getParkingMachine() {
        ParkingMachine parkingMachine = new ParkingMachine();
        parkingMachine.setId(2L);
        parkingMachine.setLatitude(789L);
        parkingMachine.setLongitude(123L);
        return parkingMachine;
    }
}

