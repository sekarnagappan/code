package com.nsekar.lil.learningspring.web;

import com.nsekar.lil.learningspring.business.GuestService;
import com.nsekar.lil.learningspring.business.GuestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class guestController {
    private final GuestService guestService;

    @Autowired
    public guestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuestList(Model model){
        List<GuestView> guestList = this.guestService.getGuestList();
        model.addAttribute("guestList", guestList);
        return "guestlist";
    }
}
