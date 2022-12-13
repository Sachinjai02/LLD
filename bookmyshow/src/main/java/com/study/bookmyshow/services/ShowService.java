package com.study.bookmyshow.services;

import com.study.bookmyshow.models.*;
import com.study.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private ShowRepository showRepository;
    private AuditoriumRepository auditoriumRepository;
    private SeatTypeShowRepository seatTypeShowRepository;
    private ShowSeatRepository showSeatRepository;

    @Autowired
    public ShowService(ShowRepository showRepository
    , AuditoriumRepository auditoriumRepository
    , SeatTypeShowRepository seatTypeShowRepository
    , ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.seatTypeShowRepository = seatTypeShowRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public Show createShow(Date start, Date end, Long audiId, Map<SeatType, Double> seatTypePrice) {
        Auditorium audi = auditoriumRepository.findById(audiId).get();
        //1. Create a Show
        Show show = new Show();
        show.setStartTime(start);
        show.setEndTime(end);
        show.setAuditorium(audi);

        Show savedShow = showRepository.save(show);
        //2. Create ShowSeats
        List<ShowSeat> showSeatList = audi.getSeatList().stream().map(seat -> {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(savedShow);
            showSeat.setSeat(seat);
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            return showSeat;
        }).collect(Collectors.toList());

        showSeatRepository.saveAll(showSeatList);

        //3. Create ShowTypeSeat

        List<SeatTypeShow> seatTypeShows = seatTypePrice.entrySet().stream().map(entry -> {
            SeatTypeShow seatTypeShow = new SeatTypeShow();
            seatTypeShow.setShow(savedShow);
            seatTypeShow.setSeatType(entry.getKey());
            seatTypeShow.setPrice(entry.getValue());
            return seatTypeShow;
        }).collect(Collectors.toList());

        this.seatTypeShowRepository.saveAll(seatTypeShows);
        return show;
    }
}
