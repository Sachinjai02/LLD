package com.study.bookmyshow.controllers;

import com.study.bookmyshow.models.Auditorium;
import com.study.bookmyshow.models.Seat;
import com.study.bookmyshow.models.SeatType;
import com.study.bookmyshow.models.Theatre;
import com.study.bookmyshow.services.TheatreService;
import com.study.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class TheatreController {

    private TheatreService theatreService;
    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;

    }

    public Theatre createTheatre(String name, String address, Long cityId) {
        return this.theatreService.createTheatre(name, address, cityId);
    }

    public Auditorium createAuditorium(Long theatreId, String name) {
        return this.theatreService.createAuditorium(theatreId, name);
    }

    public List<Seat> createSeats(Long auditoriumId, Map<SeatType, Integer> seatTypeCounts) {
        return this.theatreService.createSeats(auditoriumId, seatTypeCounts);
    }
}
