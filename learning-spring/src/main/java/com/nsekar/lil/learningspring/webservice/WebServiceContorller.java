package com.nsekar.lil.learningspring.webservice;

import com.nsekar.lil.learningspring.business.*;
import com.nsekar.lil.learningspring.data.Guest;
import com.nsekar.lil.learningspring.data.GuestRepository;
import com.nsekar.lil.learningspring.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceContorller {

    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;
    private final GuestRepository guestRepository;

    @Autowired
    public WebServiceContorller(DateUtils dateUtils, ReservationService reservationService, GuestService guestService, RoomService roomService, GuestRepository guestRepository) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.roomService = roomService;
        this.guestRepository = guestRepository;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam (value="date", required = false)String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    @RequestMapping(path="/rooms", method = RequestMethod.GET)
    public List<RoomView> getRoomsList() {
        return roomService.getRoomsList();
    }

    @RequestMapping(path="/guests", method = RequestMethod.GET)
    public List<GuestView> getGuestList(){
        return guestService.getGuestList();
    }

    @RequestMapping(path="/guests", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest newGuest){
        System.out.println(newGuest);
        return guestService.addGuest(newGuest);

    }

    //curl -X POST localhost:8080/api/guests -H 'Content-type:application/json' -d '{"firstName": "Tom", "lastName": "Sawyer", "emailAddress": "tom.saywer@tom.com", "address": "Somewhere in USA","country": "USA", "state" : "GA","phoneNumber" : "+1-800_12345678"}'
    //curl -X POST localhost:8080/api/guests -H 'Content-type:application/json' -d '{"firstName": "Huckleberry", "lastName": "Finn", "emailAddress": "huck.finn@finn.com", "address": "Roaming around in USA","country": "USA", "state" : "GA","phoneNumber" : "+1-800_1111111"}'
}
