package com.study.bookmyshow.services;

import com.study.bookmyshow.exceptions.SeatNotAvailableException;
import com.study.bookmyshow.exceptions.ShowNotFoundException;
import com.study.bookmyshow.exceptions.UserNotFoundException;
import com.study.bookmyshow.models.*;
import com.study.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TicketService {
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;

    private ShowRepository showRepository;
    private UserRepository userRepository;

    private SeatTypeShowRepository seatTypeShowRepository;
    @Autowired
    public TicketService(ShowSeatRepository showSeatRepository, TicketRepository ticketRepository
        , UserRepository userRepository
        , ShowRepository showRepository
        , SeatTypeShowRepository seatTypeShowRepository) {
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.seatTypeShowRepository = seatTypeShowRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(Long showId, List<Long> showSeatIds, Long userId) {
        /* Approach
        1. Fetch showSeats from DB
        2. Check their status, if any of them is not AVAILABLE, throw exception
        3. Else, Lock showSeats for update
        4. Check again if they are available (double-checked locking)
        5. If so, book them
         */
        List<ShowSeat> showSeats = this.showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat : showSeats) {
            if(! showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatNotAvailableException("Seat is not available for booking " + showSeat.getSeat().getName());
            }
        }

        showSeats = this.showSeatRepository.findAllByIdForUpdate(showSeatIds);
        for(ShowSeat showSeat : showSeats) {
            if(! showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new SeatNotAvailableException("Seat is not available for booking " + showSeat.getSeat().getName());
            }
            showSeat.setStatus(ShowSeatStatus.BOOKED);
        }

        this.showSeatRepository.saveAllAndFlush(showSeats);

        Ticket ticket = new Ticket();
        ticket.setBookingTime(new Date());
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticket.setShowSeatList(showSeats);
        

        Optional<User> user = userRepository.findById(userId);
        Optional<Show> show = showRepository.findById(showId);
        if(! show.isPresent()) {
            throw new ShowNotFoundException("No show found by id:" + showId);
        }
        ticket.setShow(show.get());
        if(! user.isPresent()) {
            throw new UserNotFoundException("No user found by id: " + userId);
        }
        ticket.setUser(user.get());
        ticket.setAmount(getTicketAmount(show.get(), showSeats));
        ticketRepository.save(ticket);
        return ticket;
    }

    private double getTicketAmount(Show show, List<ShowSeat> showSeats) {
        List<SeatTypeShow> seatTypeShowsByShow = this.seatTypeShowRepository.findSeatTypeShowsByShow(show);
        Map<SeatType, Double> seatTypePrices = new HashMap<>();
        seatTypeShowsByShow.forEach(seatTypeShows -> seatTypePrices.put(seatTypeShows.getSeatType(), seatTypeShows.getPrice()));
        double amount = 0d;
        for(ShowSeat seat: showSeats) {
            amount += seatTypePrices.get(seat.getSeat().getSeatType());
        }
        return amount;
    }
}
