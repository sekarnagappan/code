package com.nsekar.lil.learningspring.util;

import com.nsekar.lil.learningspring.business.ReservationService;
import com.nsekar.lil.learningspring.business.RoomReservation;
import com.nsekar.lil.learningspring.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    @Autowired
    public AppStartupEvent(RoomRepository roomRepository,
                           GuestRepository guestRepository,
                           ReservationRepository reservationRepository,
                           ReservationService reservationService,
                           DateUtils dateUtils) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);

        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);

        Date date = this.dateUtils.createDateFromDateString("2022-01-02");
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        roomReservations.forEach(System.out::println);

        LocalDate localDate = LocalDate.of(2022, Month.JANUARY, 2);
        java.sql.Date  reservationDate = java.sql.Date.valueOf(localDate);
        for(Reservation reservation : reservations){
            if (reservation.getReservationDate().equals(reservationDate)){
                System.out.println("Reservation on " + reservationDate + " are : " + reservation);
            }
        }
    }
}
