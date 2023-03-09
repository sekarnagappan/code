package com.nsekar.lil.learningspring.business;

import com.nsekar.lil.learningspring.data.Guest;
import com.nsekar.lil.learningspring.data.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestView> getGuestList() {
        List<GuestView> guestViews = new ArrayList<GuestView>();
        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(guest -> {
            GuestView guestView = new GuestView();
            guestView.setLastName(guest.getLastName());
            guestView.setFirstName(guest.getFirstName());
            guestView.setEmailAddress(guest.getEmailAddress());
            guestView.setAddress(guest.getAddress());
            guestView.setCountry(guest.getCountry());
            guestView.setState(guest.getState());
            guestView.setPhoneNumber(guest.getPhoneNumber());

            guestViews.add(guestView);
        });
        return guestViews;
    }

    public Guest addGuest(Guest guest){
        if (guest == null){
            throw new RuntimeException("Guest cannot be null");
        }
        return this.guestRepository.save(guest);
    }
}
