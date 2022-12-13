package com.study.bookmyshow.controllers;

import com.study.bookmyshow.models.SeatType;
import com.study.bookmyshow.models.Show;
import com.study.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;

@Controller
public class ShowController {
    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    public Show createShow(Date start, Date end, Long audiId, Map<SeatType, Double> seatTypePrice) {
        return this.showService.createShow(start, end, audiId, seatTypePrice);
    }
}
